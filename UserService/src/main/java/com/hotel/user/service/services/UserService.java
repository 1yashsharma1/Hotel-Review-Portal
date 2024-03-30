package com.hotel.user.service.services;

import com.hotel.user.service.payloads.UserDto;

import java.util.List;

public interface UserService {

    UserDto saveUser(UserDto user);

    List<UserDto> getAllUser();

    UserDto getUserById(String userId);

}