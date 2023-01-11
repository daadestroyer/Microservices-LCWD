package com.microservice.UserService.service;

import java.util.List;

import com.microservice.UserService.entity.User;

public interface UserService {

	public User saveUser(User user);

	public List<User> getAllUser();

	public User getSingleUser(String userId);

	public User deleteUser(String userId);
}
