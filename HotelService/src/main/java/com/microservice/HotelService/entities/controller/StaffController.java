package com.microservice.HotelService.entities.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.HotelService.entities.Stafs;

@RestController
@RequestMapping("/stafs")
public class StaffController {

	@GetMapping
	public ResponseEntity<List<Stafs>> getAllStafs() {
		List<Stafs> listofStafs = List.of(new Stafs("1001", "mohan", "100000"), new Stafs("1002", "padhy", "200000"),
				new Stafs("1003", "hanumanth", "300000"));

		return new ResponseEntity<>(listofStafs, HttpStatus.OK);
	}
}
