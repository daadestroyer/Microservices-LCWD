package com.microservice.HotelService.services;

import java.util.List;
import com.microservice.HotelService.entities.Hotel;

public interface HotelService {

	public Hotel saveHotel(Hotel hotel);

	public List<Hotel> getAllHotel();

	public Hotel getSingleHotel(String hotelId);

	public Hotel deleteHotel(String hotelId);

}
