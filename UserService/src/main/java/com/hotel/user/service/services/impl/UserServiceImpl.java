package com.hotel.user.service.services.impl;

import com.hotel.user.service.entities.User;
import com.hotel.user.service.exceptions.ResourceNotFoundException;
import com.hotel.user.service.extenal.services.HotelService;
import com.hotel.user.service.payloads.Hotel;
import com.hotel.user.service.payloads.Rating;
import com.hotel.user.service.payloads.UserDto;
import com.hotel.user.service.repositories.UserRepo;
import com.hotel.user.service.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    // --------------------------------Objects -------------------------------- //
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;


    // --------------------------------Public Methods -------------------------------- //
    @Override
    public UserDto saveUser(UserDto user) {
        String userId = UUID.randomUUID().toString();
        user.setId(userId);
        return userToDto(userRepo.save(dtoToUser(user)));
    }

    @Override

    //implement the microservice rest-template method
    // to fetch the hotel of every rating of user and display it on postman

    public List<UserDto> getAllUser() {
        List<User> userList = userRepo.findAll();
        List<UserDto> userDtoList = userList.stream().map(this::userToDto).toList();
        userDtoList.forEach(userDto -> {
            ResponseEntity<List<Rating>> response = restTemplate.exchange(
                    "http://RATING-SERVICE/ratings/users/" + userDto.getId(),
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<>() {
                    });
            List<Rating> ratingList = response.getBody();
            List<Rating> ratingWithHotelList = ratingList.stream().peek(rating ->
            {
                Hotel hotel = restTemplate.getForObject("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
                rating.setHotel(hotel);
            }).toList();
            userDto.setRatings(ratingWithHotelList);

        });

        return userDtoList;
    }

    @Override
    public UserDto getUserById(String userId) {
        UserDto userDto = userToDto(userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Resource not found for userId" + userId)));
        ResponseEntity<List<Rating>> response = restTemplate.exchange(
                "http://RATING-SERVICE/ratings/users/" + userDto.getId(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });
        List<Rating> ratingList = response.getBody();

        List<Rating> ratingWithHotelList = ratingList.stream().peek(rating ->
        {
            //Using Feign Client API
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            //Using Normal Rest api request
//           Hotel hotel=restTemplate.getForObject("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
            rating.setHotel(hotel);
        }).toList();
        userDto.setRatings(ratingWithHotelList);
        return userDto;
    }

    // --------------------------------Private Methods -------------------------------- //
    private User dtoToUser(UserDto user) {
        return this.modelMapper.map(user, User.class);
    }

    private UserDto userToDto(User user) {
        return this.modelMapper.map(user, UserDto.class);
    }
}
