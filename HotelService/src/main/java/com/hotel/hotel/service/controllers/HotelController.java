package com.hotel.hotel.service.controllers;


import com.hotel.hotel.service.payload.HotelDto;
import com.hotel.hotel.service.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    // -------------------------------- Object -------------------------------- //
    @Autowired
    private HotelService hotelService;


    // -------------------------------- Methods -------------------------------- //

     //get
    @GetMapping("/{id}")
    public ResponseEntity<HotelDto> getHotelById(@PathVariable String id) {
        return new ResponseEntity<>(hotelService.getHotelById(id), HttpStatus.OK);
    }

    //get all
    @GetMapping({"/",""})
    public ResponseEntity<List<HotelDto>> getAllHotels() {
        return new ResponseEntity<>(hotelService.getAllHotel(), HttpStatus.OK);
    }

    //post
    @PostMapping({"/",""})
    public ResponseEntity<HotelDto> saveHotel(@RequestBody HotelDto hotelDto) {
        return new ResponseEntity<>(hotelService.saveHotel(hotelDto), HttpStatus.CREATED);
    }

}
