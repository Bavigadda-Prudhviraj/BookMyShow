package org.prudhviraj.bookmyshow.models.enums;

public enum BookingStatus {
    REQUESTED,      // Booking initiated, waiting for confirmation
    PENDING,        // Payment is pending
    CONFIRMED,      // Booking successful and confirmed
    CANCELLED,      // Booking canceled by user or system
    FAILED,         // Payment or booking process failed
    EXPIRED,        // Booking window timed out without confirmation
    REFUNDED        // Booking was canceled and a refund was issued
}
