package org.prudhviraj.bookmyshow.mappers;


import org.prudhviraj.bookmyshow.dtos.CreateBookingResponseDto;
import org.prudhviraj.bookmyshow.dtos.ResponseStatus;
import org.prudhviraj.bookmyshow.models.Booking;
import org.prudhviraj.bookmyshow.models.enums.BookingStatus;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {

    public CreateBookingResponseDto toCreateBookingResponseDto(Booking booking) {
        CreateBookingResponseDto dto = new CreateBookingResponseDto();
        // Assuming Booking inherits 'id' (Long) from BaseModel
        dto.setBookingId(booking.getId());
        // Set SUCCESS if booking status is CONFIRMED, else FAILURE
        dto.setResponseStatus(booking.getStatus() == BookingStatus.CONFIRMED
                ? ResponseStatus.SUCCESS
                : ResponseStatus.FAILURE);
        return dto;
    }
}