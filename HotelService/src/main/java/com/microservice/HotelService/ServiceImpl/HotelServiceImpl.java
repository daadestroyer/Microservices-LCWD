package com.microservice.HotelService.ServiceImpl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.HotelService.entities.Hotel;
import com.microservice.HotelService.exception.ResourceNotFoundException;
import com.microservice.HotelService.repo.HotelRepo;
import com.microservice.HotelService.services.HotelService;

@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	private HotelRepo hotelRepo;

	@Override
	public Hotel saveHotel(Hotel hotel) {
		String hotelId = UUID.randomUUID().toString();
		hotel.setHotelId("HOTEL_"+hotelId);
		Hotel savedHotel = this.hotelRepo.save(hotel);
		return savedHotel;
	}

	@Override
	public List<Hotel> getAllHotel() {
		return this.hotelRepo.findAll();
	}

	@Override
	public Hotel getSingleHotel(String hotelId) {
		Hotel savedHotel = this.hotelRepo.findById(hotelId)
				.orElseThrow(() -> new ResourceNotFoundException("Hotel with " + hotelId + " not found..."));
		return savedHotel;
	}

	@Override
	public Hotel deleteHotel(String hotelId) {
		Hotel savedHotel = this.hotelRepo.findById(hotelId)
				.orElseThrow(() -> new ResourceNotFoundException("Hotel with " + hotelId + " not found..."));
		this.hotelRepo.delete(savedHotel);
		return savedHotel;
	}

}
