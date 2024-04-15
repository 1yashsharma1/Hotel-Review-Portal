package com.hotel.hotel.service.services.impl;

import com.hotel.hotel.service.entities.Hotel;
import com.hotel.hotel.service.exceptions.ResourceNotFoundException;
import com.hotel.hotel.service.payload.HotelDto;
import com.hotel.hotel.service.repositories.HotelRepo;
import com.hotel.hotel.service.services.HotelService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    // --------------------------------Objects -------------------------------- //
    @Autowired
    private HotelRepo hotelRepo;

    @Autowired
    private ModelMapper modelMapper;


    // --------------------------------Public Methods -------------------------------- //
    @Override
    public HotelDto saveHotel(HotelDto hotelDto) {
        String hotelId = UUID.randomUUID().toString();
        hotelDto.setId(hotelId);
        return hotelToDto(hotelRepo.save(dtoToHotel(hotelDto)));
    }

    @Override
    public List<HotelDto> getAllHotel() {
        List<Hotel> hotelList = hotelRepo.findAll();
        return hotelList.stream().map(this::hotelToDto).toList();
    }

    @Override
    public HotelDto getHotelById(String hotelId) {
        return hotelToDto(hotelRepo.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("Hotel Not Found for id " + hotelId)));
    }


    // --------------------------------Private Methods -------------------------------- //
    private Hotel dtoToHotel(HotelDto hotelDto) {
        return modelMapper.map(hotelDto, Hotel.class);
    }

    private HotelDto hotelToDto(Hotel hotel) {
        return modelMapper.map(hotel, HotelDto.class);
    }
}
