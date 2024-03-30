package com.hotel.user.service.payloads;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
public class UserDto {

    private String id;

    private String name;

    private String email;

    private String about;

    private List<Rating> ratings=new ArrayList<>();

}