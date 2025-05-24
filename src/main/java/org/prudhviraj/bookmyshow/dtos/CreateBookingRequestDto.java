package org.prudhviraj.bookmyshow.dtos;

import lombok.*;

import java.util.List;

@Data
public class CreateBookingRequestDto {
    private Long userId;
    private Long showId; // we can skip this as we can get showId from showSeatId
    //this size should be considered as quantity
    private List<Long> showSeatIds;
}
