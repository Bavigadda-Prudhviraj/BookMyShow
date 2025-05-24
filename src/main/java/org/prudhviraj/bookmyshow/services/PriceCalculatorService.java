package org.prudhviraj.bookmyshow.services;

import lombok.AllArgsConstructor;
import org.prudhviraj.bookmyshow.exceptions.*;
import org.prudhviraj.bookmyshow.models.*;
import org.prudhviraj.bookmyshow.repositories.ShowSeatTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class PriceCalculatorService {
    private final ShowSeatTypeRepository showSeatTypeRepository;

    public double calculateAmount(Show show, List<ShowSeat> showSeats) {
        if (showSeats == null || showSeats.isEmpty()) {
            throw new IllegalArgumentException("No show seats provided.");
        }

        List<ShowSeatType> showSeatTypes = showSeatTypeRepository.findAllByShow(show);
        if (showSeatTypes == null || showSeatTypes.isEmpty()) {
            throw new PricingNotConfiguredException("No pricing configured for show ID: " + show.getId());
        }

        double amount = 0.0;
        for (ShowSeat seat : showSeats) {
            boolean matched = false;
            for (ShowSeatType showSeatType : showSeatTypes) {
                if (seat.getSeat().getSeatType().equals(showSeatType.getSeatType())) {
                    amount += showSeatType.getPrice();
                    matched = true;
                    break;
                }
            }
            if (!matched) {
                throw new SeatTypePricingNotFoundException(
                        "No price found for SeatType: " + seat.getSeat().getSeatType() +
                                " in show ID: " + show.getId()
                );
            }
        }

        return amount;
    }
}
