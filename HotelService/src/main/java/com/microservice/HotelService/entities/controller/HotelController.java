package com.microservice.HotelService.entities.controller;

import java.util.List;

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

import com.microservice.HotelService.ServiceImpl.HotelServiceImpl;
import com.microservice.HotelService.entities.Hotel;


@RestController
@RequestMapping("/hotels")
public class HotelController {

	@Autowired
	private HotelServiceImpl hotelServiceImpl;

	//
	@PostMapping("/")
	public ResponseEntity<?> createHotel(@RequestBody Hotel hotel) {
		Hotel savedHotel = this.hotelServiceImpl.saveHotel(hotel);
		return new ResponseEntity<>(savedHotel, HttpStatus.CREATED);
	}

	@GetMapping("/{hotelId}")
	public ResponseEntity<?> getSingleHotel(@PathVariable String hotelId) {
		Hotel singleHotel = this.hotelServiceImpl.getSingleHotel(hotelId);
		return new ResponseEntity<>(singleHotel, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<?> getAllHotel() {
		List<Hotel> allHotel = this.hotelServiceImpl.getAllHotel();
		return new ResponseEntity<>(allHotel, HttpStatus.OK);
	}

	@DeleteMapping("/{hotelId}")
	public ResponseEntity<?> deleteHotelById(@PathVariable String hotelId) {
		Hotel deletedHotel = this.hotelServiceImpl.deleteHotel(hotelId);
		return new ResponseEntity<>(deletedHotel, HttpStatus.OK);
	}

}
