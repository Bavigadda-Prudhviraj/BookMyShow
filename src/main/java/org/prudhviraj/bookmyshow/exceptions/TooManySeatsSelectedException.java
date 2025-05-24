package org.prudhviraj.bookmyshow.exceptions;

public class TooManySeatsSelectedException extends RuntimeException {
    public TooManySeatsSelectedException(String message) {
        super(message);
    }
}
