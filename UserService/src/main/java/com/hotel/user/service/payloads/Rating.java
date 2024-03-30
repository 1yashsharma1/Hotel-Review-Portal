package com.hotel.user.service.payloads;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Rating {
    private int rating;
    private String ratingId;
    private String userId;
    private String hotelId;
    private String feedback;
    private Hotel hotel;

}
