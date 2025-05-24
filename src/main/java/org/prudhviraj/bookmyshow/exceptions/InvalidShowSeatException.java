package org.prudhviraj.bookmyshow.exceptions;

import java.util.Set;

public class InvalidShowSeatException extends RuntimeException {
    public InvalidShowSeatException(Set<Long> invalidIds) {
        super("Invalid ShowSeat IDs: " + invalidIds);
    }
}

