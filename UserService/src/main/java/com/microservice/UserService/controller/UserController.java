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


@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserServiceImpl userServiceImpl;
	
	 

	//
	@PostMapping("/")
	public ResponseEntity<?> createUser(@RequestBody User user) {
		User saveUser = this.userServiceImpl.saveUser(user);
		return new ResponseEntity<>(saveUser, HttpStatus.CREATED);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<?> getSingleUser(@PathVariable String userId) {
		User singleUser = this.userServiceImpl.getSingleUser(userId);
		return new ResponseEntity<>(singleUser, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<?> getAllUser() {
		List<User> allUser = this.userServiceImpl.getAllUser();
		return new ResponseEntity<>(allUser, HttpStatus.OK);
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUserById(@PathVariable String userId){
		User deleteUser = this.userServiceImpl.deleteUser(userId);
		return new ResponseEntity<>(deleteUser,HttpStatus.OK);
	}
}
