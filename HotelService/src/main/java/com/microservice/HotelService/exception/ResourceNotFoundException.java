package com.microservice.HotelService.exception;

public class ResourceNotFoundException extends RuntimeException {
	
	
	public ResourceNotFoundException(String message) {
		super(message);
	}
}
