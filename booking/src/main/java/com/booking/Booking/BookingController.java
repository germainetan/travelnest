package com.booking.Booking;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    // create a booking record
    @PostMapping()
    public ResponseEntity<Booking> add_booking(@RequestBody Booking booking) {
        return new ResponseEntity<>(bookingService.add_booking(booking), CREATED);
    }


    // return all booking record
    @GetMapping()
    public ResponseEntity<List<Booking>> get_all_bookings() {
        return new ResponseEntity<>(bookingService.get_all_bookings(), OK);
    }

    @GetMapping("/{bookingID}")
    public Optional get_booking_by_bookingID(@PathVariable("bookingID") Integer bookingID) {
        return bookingService.get_booking_by_bookingID(bookingID);
    }


    // return all propertyID that has booking status in ("Confirmed", "Pending") and by query_start_datetime < record_end_datetime
    @GetMapping("/search")
    public ResponseEntity<List<String>> get_by_filter_condition (
            @RequestParam(required = false) String start_datetime) {
        LocalDateTime parsedDateTime = LocalDateTime.parse(start_datetime);
        List <String> status_list = List.of("confirmed", "pending");

        List <String> booking = bookingService.get_by_filter_condition(status_list , parsedDateTime);

        return ResponseEntity.ok(booking);
    }


    // return booking record by ownerID and booking_status
    @GetMapping("/search_owner")
    public ResponseEntity<List<Booking>> get_by_owner_and_booking_status (
            @RequestParam Integer ownerID,
            @RequestParam String booking_status) {

        List<Booking> booking = bookingService.get_by_owner_and_booking_status(ownerID, booking_status);

        return ResponseEntity.ok(booking);
    }


    // return booking record by renterID and booking_status
    @GetMapping("/search_renter")
    public ResponseEntity<List<Booking>> get_by_renter_and_booking_status (
            @RequestParam Integer renterID,
            @RequestParam String booking_status) {

        List<Booking> booking = bookingService.get_by_renter_and_booking_status(renterID, booking_status);

        return ResponseEntity.ok(booking);
    }


    // update booking status
    @PutMapping("/{bookingID}")
    public ResponseEntity<Booking> update_booking_status(
            @PathVariable("bookingID") Integer bookingID,
            @RequestParam String booking_status) {

        return new ResponseEntity<>(bookingService.update_booking_status(bookingID, booking_status), OK);
    }

}
