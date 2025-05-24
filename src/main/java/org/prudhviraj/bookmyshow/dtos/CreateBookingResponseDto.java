package org.prudhviraj.bookmyshow.dtos;

import lombok.*;

@Data
public class CreateBookingResponseDto {
    private Long BookingId;
    private ResponseStatus responseStatus;

}
