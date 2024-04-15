package com.hotel.user.service.payloads;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@Builder
public class UserDto {

    private String id;

    private String name;

    private String email;

    private String about;

    private List<Rating> ratings = new ArrayList<>();

    public UserDto(String id, String name, String email, String about, List<Rating> ratings) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.about = about;
        this.ratings = ratings;
    }

}