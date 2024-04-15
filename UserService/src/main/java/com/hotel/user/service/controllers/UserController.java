package com.hotel.user.service.controllers;

import com.hotel.user.service.payloads.UserDto;
import com.hotel.user.service.services.UserService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(UserController.class);

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


    int ratingRetry=1;
    //get by id
    @GetMapping("/{id}")
   // @CircuitBreaker(name = "ratingToHotelCircuitBreaker",fallbackMethod = "ratingToHotelFallback")
   // @Retry(name = "ratingToHotelService",fallbackMethod = "ratingToHotelFallback")
    @RateLimiter(name = "ratingToHotelRateLimiter",fallbackMethod = "ratingToHotelFallback")
    public ResponseEntity<UserDto> getUserById(@PathVariable String id){
        logger.info("Retry Count : {}",ratingRetry);
        ratingRetry++;
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }


    public ResponseEntity<UserDto> ratingToHotelFallback(String userId,Exception ex){
        logger.info("Fallback is excecuted because service is down"+ex.getMessage());
        UserDto user = UserDto.builder().email("DummyEmail@gmail").name("Dummy").id("-1121").about("Dummy about").build();
        return new ResponseEntity<>(user,HttpStatus.SERVICE_UNAVAILABLE);

    }


}
