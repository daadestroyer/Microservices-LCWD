package com.microservice.UserService.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.microservice.UserService.ServiceImpl.UserServiceImpl;
import com.microservice.UserService.entity.User;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

	@Autowired
	private UserServiceImpl userServiceImpl;

	//
	@PostMapping("/")
	public ResponseEntity<?> createUser(@RequestBody User user) {
		User saveUser = this.userServiceImpl.saveUser(user);
		return new ResponseEntity<>(saveUser, HttpStatus.CREATED);
	}
	
	int retryCount = 1;

	@GetMapping("/{userId}")
//	@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
	@Retry(name = "ratingHotelRetry" , fallbackMethod = "ratingHotelFallback")
	public ResponseEntity<?> getSingleUser(@PathVariable String userId) {
		log.info("Retry Count :"+retryCount);
		retryCount++;
		User singleUser = this.userServiceImpl.getSingleUser(userId);
		
		return new ResponseEntity<>(singleUser, HttpStatus.OK);
	}

	// fallback method for rating_hotel_breaker
	public ResponseEntity<?> ratingHotelFallback(Exception ex) {
		System.out.println("==================FALL BACK METHOD CALLED=========================");
		log.info("Fallback method executed", ex.getMessage());
		User user = User.builder().userId("1111").email("dummy@gmail.com").name("dummy user")
				.about("dummy user created because some services are down").build();
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<?> getAllUser() {
		List<User> allUser = this.userServiceImpl.getAllUser();
		return new ResponseEntity<>(allUser, HttpStatus.OK);
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUserById(@PathVariable String userId) {
		User deleteUser = this.userServiceImpl.deleteUser(userId);
		return new ResponseEntity<>(deleteUser, HttpStatus.OK);
	}
}
