package com.microservice.UserService.ServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservice.UserService.entity.Hotel;
import com.microservice.UserService.entity.Ratings;
import com.microservice.UserService.entity.User;
import com.microservice.UserService.exception.ResourceNotFoundException;
import com.microservice.UserService.repo.UserRepo;
import com.microservice.UserService.service.UserService;

import ch.qos.logback.classic.Logger;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public User saveUser(User user) {
		String randomId = UUID.randomUUID().toString();
		user.setUserId("USER_" + randomId);
		return userRepo.save(user);
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		String URL = "http://RATING-SERVICE/ratings";

		Ratings[] ratingsArray = this.restTemplate.getForObject(URL, Ratings[].class);
		
		List<Ratings> allRatings = Arrays.asList(ratingsArray);
		
		List<User> allUser = userRepo.findAll();

		List<User> grossUser = allUser.stream().map(user->{
			
			List<Ratings> modifiedRatings = allRatings.stream().filter(ratings->ratings.getUserId().equals(user.getUserId())) .collect(Collectors.toList());
			
			
			List<Ratings> modifiedRatings2 = modifiedRatings.stream().map(rating->{
				String hotelURL = "http://HOTEL-SERVICE/hotels/" + rating.getHotelId();
				ResponseEntity<Hotel> forEntity = this.restTemplate.getForEntity(hotelURL, Hotel.class);
				Hotel hotel = forEntity.getBody();
				rating.setHotel(hotel);
				return rating;
			}).collect(Collectors.toList());
			
			user.setRatings(modifiedRatings2);
			return user;
			 
		}).collect(Collectors.toList());
		
	
		return grossUser;
	}

	@Override
	public User getSingleUser(String userId) {
		// getting single user
		User savedUser = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id : " + userId));

		// fetch rating of above user with userId
		// URL: http://localhost:8083/ratings/getratingbyuserid/{userId}
		String getRatingsByUserIdURL = "http://RATING-SERVICE/ratings/getratingbyuserid/" + userId;
		
		Ratings[] listOfRatingsByUserIdArray = this.restTemplate.getForObject(getRatingsByUserIdURL, Ratings[].class);

		List<Ratings> ratings = Arrays.asList(listOfRatingsByUserIdArray);

		List<Ratings> listOfRatings = ratings.stream().map(rating -> {

			String hotelURL = "http://HOTEL-SERVICE/hotels/" + rating.getHotelId();
			ResponseEntity<Hotel> forEntity = this.restTemplate.getForEntity(hotelURL, Hotel.class);
			Hotel hotel = forEntity.getBody();

			rating.setHotel(hotel);
			return rating;

		}).collect(Collectors.toList());

		savedUser.setRatings(listOfRatings);

		return savedUser;
	}

	@Override
	public User deleteUser(String userId) {
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id : " + userId));
		userRepo.delete(user);
		return user;
	}

}
