package org.prudhviraj.bookmyshow.models;
import org.prudhviraj.bookmyshow.models.enums.BookingStatus;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@Entity
public class Booking extends BaseModel {

    private String bookingId;

    /**
     * A booking is made by a single user.
     * Many bookings can belong to one user.
     */
    @ManyToOne//(fetch = FetchType.LAZY)
    private User user;

    /**
     * A booking is for one show.
     * Many bookings can exist for the same show.
     */
    @ManyToOne//(fetch = FetchType.LAZY)
    private Show show;

    /**
     * A booking contains multiple show seats.
     * Each show seat may be booked in multiple bookings only in special cases
     * (but usually it's one-to-many; you might consider using @OneToMany with @JoinColumn).
     */
    @ManyToMany
//    @JoinTable(
//            name = "booking_show_seats",
//            joinColumns = @JoinColumn(name = "booking_id"),
//            inverseJoinColumns = @JoinColumn(name = "show_seat_id")
//    )
    private List<ShowSeat> showSeats;

    /**
     * A booking may have multiple payments (retries, partial payments).
     * Usually it's a one-to-one, but you can keep it flexible.
     */
    @OneToMany//(mappedBy = "booking", cascade = CascadeType.ALL)
    private List<Payment> payments;

    private int quantity;

    private double amount;

    /**
     * Enum to represent the status of the booking (CONFIRMED, CANCELLED, etc.).
     * Using EnumType. STRING is safer for DB readability and future-proofing.
     */
    @Enumerated(EnumType.STRING)
    private BookingStatus status;
}
