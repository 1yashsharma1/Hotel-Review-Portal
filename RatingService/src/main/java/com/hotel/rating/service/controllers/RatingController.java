package com.hotel.rating.service.controllers;

import com.hotel.rating.service.payload.RatingDto;
import com.hotel.rating.service.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @GetMapping({"/", ""})
    public ResponseEntity<List<RatingDto>> getAllRatings() {
        return new ResponseEntity<>(ratingService.getAllRating(), HttpStatus.OK);
    }

    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<RatingDto>> getAllRatingsByHotelId(@PathVariable String hotelId) {
        return new ResponseEntity<>(ratingService.getAllRatingByHotelId(hotelId), HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<RatingDto>> getAllRatingsByUserId(@PathVariable String userId) {
        return new ResponseEntity<>(ratingService.getAllRatingByUserId(userId), HttpStatus.OK);
    }

    @PostMapping({"/", ""})
    public ResponseEntity<RatingDto> createRating(@RequestBody RatingDto ratingDto) {
        return new ResponseEntity<>(ratingService.createRating(ratingDto), HttpStatus.CREATED);
    }
}
