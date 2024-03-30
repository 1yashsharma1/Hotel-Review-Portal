package com.hotel.hotel.service.payload;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HotelDto {
    private String id;
    private String name;
    private String location;
    private String about;
}
