package com.hotel.rating.service.services;

import com.hotel.rating.service.payload.RatingDto;

import java.util.List;

public interface RatingService {

    //create
    RatingDto createRating(RatingDto ratingDto);

    //get all rating
    List<RatingDto> getAllRating();

    //get all by userId
    List<RatingDto> getAllRatingByUserId(String userId);

    //get all by hotelId
    List<RatingDto> getAllRatingByHotelId(String hotelId);

}
