package com.hotel.user.service.payloads;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Hotel {
    private String id;
    private String name;
    private String location;
    private String about;

}
