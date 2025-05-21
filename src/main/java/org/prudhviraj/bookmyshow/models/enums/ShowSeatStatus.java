package org.prudhviraj.bookmyshow.models.enums;

public enum ShowSeatStatus {
    AVAILABLE,       // Seat is open for booking
    HELD,            // Temporarily locked during user selection
    RESERVED,        // Payment initiated or seat locked
    BOOKED,          // Booking confirmed and paid
    SOLD,            // (optional duplicate of BOOKED can be removed or merged)
    BLOCKED,         // Blocked for maintenance, staff, or admin
    NOT_AVAILABLE,   // Unavailable for booking due to layout or configuration
    CANCELLED,       // Previously booked, now canceled
    EXPIRED          // Held or reserved seat expired due to timeout
}
