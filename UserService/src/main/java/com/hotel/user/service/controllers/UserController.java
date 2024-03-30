package com.hotel.user.service.controllers;

import com.hotel.user.service.payloads.UserDto;
import com.hotel.user.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    //get

    @GetMapping({"","/"})
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }

    //save
    @PostMapping({"","/"})
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto user){
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    //get by id
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String id){
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

}
