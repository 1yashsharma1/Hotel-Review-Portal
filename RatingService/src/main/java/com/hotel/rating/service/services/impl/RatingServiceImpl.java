package com.hotel.rating.service.services.impl;

import com.hotel.rating.service.entities.Rating;
import com.hotel.rating.service.payload.RatingDto;
import com.hotel.rating.service.repositories.RatingRepo;
import com.hotel.rating.service.services.RatingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    // -------------------------------- Dependency Injection -------------------------------- //
    @Autowired
    private RatingRepo ratingRepo;

    @Autowired
    private ModelMapper modelMapper;

    // -------------------------------- Public Methods-------------------------------- //
    @Override
    public RatingDto createRating(RatingDto ratingDto) {
        return ratingToDto(ratingRepo.save(dtoToRating(ratingDto)));
    }

    @Override
    public List<RatingDto> getAllRating() {
        List<Rating> ratingList = ratingRepo.findAll();
        return ratingList.stream().map(this::ratingToDto).toList();
    }

    @Override
    public List<RatingDto> getAllRatingByUserId(String userId) {
        List<Rating> ratingList = ratingRepo.findByUserId(userId);
        return ratingList.stream().map(this::ratingToDto).toList();
    }

    @Override
    public List<RatingDto> getAllRatingByHotelId(String hotelId) {
        List<Rating> ratingList = ratingRepo.findByHotelId(hotelId);
        return ratingList.stream().map(this::ratingToDto).toList();
    }


    // -------------------------------- Private Methods-------------------------------- //
    private Rating dtoToRating(RatingDto ratingDto) {
        return modelMapper.map(ratingDto, Rating.class);
    }

    private RatingDto ratingToDto(Rating rating) {
        return modelMapper.map(rating, RatingDto.class);
    }
}
