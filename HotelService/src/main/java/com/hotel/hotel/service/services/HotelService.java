package com.hotel.hotel.service.services;

import com.hotel.hotel.service.payload.HotelDto;

import java.util.List;

public interface HotelService {
    HotelDto saveHotel(HotelDto hotel);

    List<HotelDto> getAllHotel();

    HotelDto getHotelById(String hotelId);

}
