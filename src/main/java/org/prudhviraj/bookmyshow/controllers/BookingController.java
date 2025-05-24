package org.prudhviraj.bookmyshow.controllers;

import lombok.AllArgsConstructor;
import org.prudhviraj.bookmyshow.dtos.*;
import org.prudhviraj.bookmyshow.mappers.BookingMapper;
import org.prudhviraj.bookmyshow.models.Booking;
import org.prudhviraj.bookmyshow.services.BookingService;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class BookingController {
    private final BookingService bookingService;
    private final BookingMapper bookingMapper;
    public CreateBookingResponseDto createBooking(CreateBookingRequestDto request) {
        CreateBookingResponseDto responseDto = new CreateBookingResponseDto();
        try {
            Booking booking = bookingService.createBooking(request.getUserId(), request.getShowId(), request.getShowSeatIds());
            responseDto = bookingMapper.toCreateBookingResponseDto(booking);
        } catch (Exception e) {
            responseDto.setBookingId(null);
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;

    }
}
