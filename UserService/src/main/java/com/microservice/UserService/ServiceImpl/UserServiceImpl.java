package com.microservice.UserService.ServiceImpl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.UserService.entity.User;
import com.microservice.UserService.exception.ResourceNotFoundException;
import com.microservice.UserService.repo.UserRepo;
import com.microservice.UserService.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Override
	public User saveUser(User user) {
		String randomId = UUID.randomUUID().toString();
		user.setUserId(randomId);
		return userRepo.save(user);
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return userRepo.findAll();
	}

	@Override
	public User getSingleUser(String userId) {
		// TODO Auto-generated method stub
		return userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id : " + userId));
	}

	@Override
	public User deleteUser(String userId) {
		User user = userRepo.findById(userId)
		.orElseThrow(() -> new ResourceNotFoundException("User not found with id : " + userId));
		userRepo.delete(user);
		return user;
	}

}
