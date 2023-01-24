package com.microservice.HotelService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HotelServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelServiceApplication.class, args);
		System.out.println("----------");
		System.out.println("Hotel Service STARTED at 8082");
		System.out.println("----------");
	}

}
 