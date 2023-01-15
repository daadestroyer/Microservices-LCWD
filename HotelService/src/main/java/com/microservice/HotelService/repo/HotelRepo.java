package com.microservice.HotelService.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.HotelService.entities.Hotel;

public interface HotelRepo extends JpaRepository<Hotel, String> {

}
