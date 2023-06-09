package com.booking.Booking;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

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
        return bookingRepository.save(booking);
    }


    // get all booking record
    public List<Booking> get_all_bookings() {
        return bookingRepository.findAll();
    }


    // get booking by bookingID
    public Optional get_booking_by_bookingID(Integer bookingID) {
        return bookingRepository.findById(bookingID);
    }


    // return all propertyID that has booking status in ("Confirmed", "Pending") and by query_start_datetime < record_end_datetime
    public List<Integer> get_by_filter_condition(List <String> status_list, LocalDateTime start_datetime, LocalDateTime end_datetime){
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


    // return all owner bookings
    public List<Booking> get_all_owner_bookings(Integer ownerID){
        return bookingRepository.get_all_owner_bookings(ownerID);
    }


    // return all renter bookings
    public List<Booking> get_all_renter_bookings(Integer renterID){
        return bookingRepository.get_all_renter_bookings(renterID);
    }


    @Transactional
    public Booking update_booking_status(Integer bookingID, String booking_status) {

        Booking booking = bookingRepository.getReferenceById(bookingID);

        if (booking_status != null) {
            booking.setBooking_status(booking_status);
        }

        return bookingRepository.save(booking);
    }


}
