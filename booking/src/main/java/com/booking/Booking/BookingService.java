package com.booking.Booking;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    // create a booking record
    public Booking add_booking(Booking booking) {

        Integer bookingID = booking.getBookingID();

        if (bookingID != null) {

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please check if you had accidentally parsed in bookingID in your request body. If yes, please remove it.");
        }

        return bookingRepository.save(booking);
    }

    // get all booking record
    public List<Booking> get_all_bookings() {
        return bookingRepository.findAll();
    }

    // get booking by bookingID
    public Optional get_booking_by_bookingID(Integer bookingID) {

        boolean exits = bookingRepository.existsById(bookingID);

        if (!exits) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Booking with bookingID " + bookingID + " does not exist");
        }

        return bookingRepository.findById(bookingID);
    }

    // return all propertyID that has booking status in ("Confirmed", "Pending") and by query_start_datetime < record_end_datetime

    public List<String> get_by_filter_condition(List <String> status_list, LocalDateTime start_datetime, LocalDateTime end_datetime){
        return bookingRepository.find_Booking_by_Status_and_DateTime(status_list, start_datetime, end_datetime);
    }

    // return booking record by ownerID and booking_status
    public List<Booking> get_by_owner_and_booking_status(Integer ownerID, String booking_status){
        return bookingRepository.get_by_owner_and_booking_status(ownerID, booking_status);
    }

    // return booking record by renterID and booking_status
    public List<Booking> get_by_renter_and_booking_status(Integer renterID, String booking_status){
        return bookingRepository.get_by_renter_and_booking_status(renterID, booking_status);
    }

    @Transactional
    public Booking update_booking_status(Integer bookingID, String booking_status) {

        boolean exits = bookingRepository.existsById(bookingID);

        if (!exits) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Booking with bookingID " + bookingID + " does not exist");
        }

        Booking booking = bookingRepository.getReferenceById(bookingID);

        if (booking_status != null) {
            booking.setBooking_status(booking_status);
        }

        return bookingRepository.save(booking);
    }


}
