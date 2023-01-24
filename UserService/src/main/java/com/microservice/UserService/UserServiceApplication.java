package com.microservice.UserService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
		System.out.println("-----------------------------");
		System.out.println("User Service STARTED at 8081");
		System.out.println("-----------------------------");
	}

}
