package org.prudhviraj.bookmyshow.services;

import lombok.AllArgsConstructor;
import org.prudhviraj.bookmyshow.exceptions.*;
import org.prudhviraj.bookmyshow.models.*;
import org.prudhviraj.bookmyshow.models.enums.*;
import org.prudhviraj.bookmyshow.repositories.*;
import org.prudhviraj.bookmyshow.util.IdGenerator;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.prudhviraj.bookmyshow.models.Booking;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookingService {

    private UserRepository userRepository;
    private ShowRepository showRepository;
    private ShowSeatRepository showSeatRepository;
    private BookingRepository bookingRepository;
    private PriceCalculatorService priceCalculatorService;
    private IdGenerator idGenerator;



    ///  her we are implementing the first approach
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking createBooking(Long userId, Long showId, List<Long> showSeatIds) throws UserNotFoundException, ShowNotFoundException {
    /**
     1. Fetch the user using the provided userId.
     2. Fetch the show using the given showId.
     3. Retrieve the list of ShowSeats using the provided showSeatIds.

     ----------- Take Lock on ShowSeats -----------

     4. Check if all selected ShowSeats are in AVAILABLE status.
     5. If any seat is not available, throw an exception (e.g., SeatUnavailableException).
     6. If all seats are available, mark each ShowSeat as BLOCKED.

     ----------- Release Lock on ShowSeats -----------

     7. Persist the updated seat statuses to the database.
     8. Create a new Booking entry with status set to PENDING.
     9. Return the Booking object.
     */
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id " + userId));

        Show show = showRepository.findById(showId)
                .orElseThrow(() -> new ShowNotFoundException("Show not found with id " + showId));

        List<ShowSeat> showSeats = validateAndFetchShowSeats(showSeatIds);
        // Step 4: Lock & Validate Availability
        for (ShowSeat seat : showSeats) {
            // Optional: Use optimistic/pessimistic locking here
            if (seat.getShowSeatStatus() != ShowSeatStatus.AVAILABLE) {
                throw new SeatUnavailableException("Seat " + seat.getId() + " is not available.");
            }
        }

        // Step 5: Mark Seats as BLOCKED (temporarily reserved)
        for (ShowSeat seat : showSeats) {
            seat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
        }
        showSeatRepository.saveAll(showSeats);

        // Step 6: Create a Booking with PENDING status
        Booking booking = Booking.builder()
                .bookingId(idGenerator.generateBookingId())
                .user(user)
                .show(show)
                .showSeats(showSeats)
                .status(BookingStatus.PENDING)
                .quantity(showSeats.size())
                .amount(priceCalculatorService.calculateAmount(show,showSeats))
                .payments(new ArrayList<>())
                .build();

        for (ShowSeat seat : showSeats) {
            seat.setShowSeatStatus(ShowSeatStatus.BOOKED);
        }
        showSeatRepository.saveAll(showSeats);
        return bookingRepository.save(booking);



    }


    public List<ShowSeat> validateAndFetchShowSeats(List<Long> showSeatIds) {
        if (showSeatIds == null || showSeatIds.isEmpty()) {
            throw new IllegalArgumentException("ShowSeat ID list must not be empty.");
        }
        if(showSeatIds.size() > 10){
            throw new TooManySeatsSelectedException("You can select maximum 10 seats.");
        }

        List<ShowSeat> showSeats = showSeatRepository.findAllById(showSeatIds);

        Set<Long> foundIds = showSeats.stream()
                .map(ShowSeat::getId)
                .collect(Collectors.toSet());

        Set<Long> invalidIds = new HashSet<>(showSeatIds);
        invalidIds.removeAll(foundIds);

        if (!invalidIds.isEmpty()) {
            throw new InvalidShowSeatException(invalidIds);
        }

        return showSeats;
    }

}
/*
When two users try to book tickets that include overlapping seats, we need to implement a locking mechanism.

# Solution 1:
    When a user clicks on "Book Now", we begin a transaction that locks the seats they are attempting to book.
    This transaction remains active until either the payment is completed or the user exits the booking process.

    Note: As soon as the user clicks "Book Now", the seats are locked. However, other users may still see those seats as available.
    If another user selects the same seat and proceeds, they might end up with an error like "Something went wrong" — which creates a poor user experience.

    While this approach is functionally correct, it's not optimal because the transaction stays open for a long duration, which is not advisable.

    Important: Acquiring locks only in code is not reliable in distributed systems. Since the code can run on multiple servers, we must acquire the lock at the **database level**, which is the single source of truth. Seat selection must happen within a database-level transactional lock.

# Solution 2:
    Instead of holding a transaction open throughout the entire booking process, we can optimize using the `status` attribute of `ShowSeat`.

    → We do not keep the transaction running for the full duration of the booking.

    Example scenario:
    - User A is booking seats 1, 2, 3
    - User B is booking seats 2, 3, 4.
    There is a conflict on seats 2 and 3.

    To handle this:
    1. We use the **serializable** isolation level in the database.
    2. The first user checks if seats are available and starts a timer.
    3. The system sets the status of those seats to **BLOCKED**.
    4. The transaction then releases the lock — no long-running transaction is needed.

    Now, other users can see the status of those seats as **BLOCKED**. We're not locking the rows anymore, but we’ve **soft-locked** them using the status field.

    If the payment is completed within the timeout period, → status changes to **BOOKED**.
    If not, → status is reset to **AVAILABLE**.
*/

