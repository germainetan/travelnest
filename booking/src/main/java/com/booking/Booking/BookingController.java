package com.booking.Booking;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

import static org.springframework.http.HttpStatus.*;

@CrossOrigin
@RestController
@RequestMapping("/booking")
public class BookingController {

    //placeholder
    private final BookingService bookingService;
    private final BookingRepository bookingRepository;

    public BookingController(BookingService bookingService, BookingRepository bookingRepository) {
        this.bookingService = bookingService;
        this.bookingRepository = bookingRepository;
    }


    // create a booking record
    @PostMapping()
    public ResponseEntity<?> add_booking(@RequestBody Booking booking) {

        Integer bookingID = booking.getBookingID();

        if (bookingID != null) {

            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("code", BAD_REQUEST.value());
            responseBody.put("data", "Please check if you had accidentally parsed in bookingID in your request body. If yes, please remove it.");
            return ResponseEntity.status(BAD_REQUEST).body(responseBody);

        }

        Booking createBooking = bookingService.add_booking(booking);

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("code", CREATED.value());
        responseBody.put("data", createBooking);
        return ResponseEntity.status(CREATED).body(responseBody);

    }


    // return all booking record
    @GetMapping()
    public ResponseEntity<?> get_all_bookings() {

        List<Booking> allbooking = bookingService.get_all_bookings();

        if (allbooking.isEmpty()) {
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("code", NOT_FOUND.value());
            responseBody.put("data", "There are no booking records");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
        }

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("code", OK.value());
        responseBody.put("data", allbooking);
        return ResponseEntity.ok(responseBody);

    }

    @GetMapping("/{bookingID}")
    public ResponseEntity<Map<String, Object>> get_booking_by_bookingID(@PathVariable("bookingID") Integer bookingID) {

        Optional booking = bookingService.get_booking_by_bookingID(bookingID);

        if (booking.isEmpty()) {
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("code", NOT_FOUND.value());
            responseBody.put("data", "Booking with bookingID " + bookingID + " does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
        }

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("code", OK.value());
        responseBody.put("data", booking.get());
        return ResponseEntity.ok(responseBody);

    }

    // return all propertyID that has booking status in ("Confirmed", "Pending")
    // and by query_start_datetime < record_end_datetime and query_start_datetime > record_start_datetime

    @GetMapping("/search")
    public ResponseEntity<?> get_by_filter_condition(
            @RequestParam String start_datetime,
            @RequestParam String end_datetime) {
        LocalDateTime parsed_start_datetime = LocalDateTime.parse(start_datetime);
        LocalDateTime parsed_end_datetime = LocalDateTime.parse(end_datetime);
        List<String> status_list = List.of("confirmed", "pending");

        List<Integer> booking = bookingService.get_by_filter_condition(status_list, parsed_start_datetime, parsed_end_datetime);

        if (booking.isEmpty()) {
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("code", NOT_FOUND.value());
            responseBody.put("data", booking);
            responseBody.put("message", "No clashed bookings");
            return ResponseEntity.status(NOT_FOUND).body(responseBody);
        }

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("code", OK.value());
        responseBody.put("data", booking);

        return ResponseEntity.ok(responseBody);
    }

    private ResponseEntity<?> getResponseEntityforsearchperson(@RequestParam String booking_status, List<Booking> booking) {
        if (booking.isEmpty()) {
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("code", NOT_FOUND.value());
            responseBody.put("data", "No " + booking_status + " booking records");
            return ResponseEntity.status(NOT_FOUND).body(responseBody);
        }

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("code", OK.value());
        responseBody.put("data", booking);

        return ResponseEntity.ok(responseBody);
    }


    // return booking record by ownerID and booking_status
    @GetMapping("/search_owner")
    public ResponseEntity<?> get_by_owner_and_booking_status (
            @RequestParam Integer ownerID,
            @RequestParam String booking_status) {

        List<Booking> booking = bookingService.get_by_owner_and_booking_status(ownerID, booking_status);

        return getResponseEntityforsearchperson(booking_status, booking);
    }


    // return booking record by renterID and booking_status
    @GetMapping("/search_renter")
    public ResponseEntity<?> get_by_renter_and_booking_status (
            @RequestParam Integer renterID,
            @RequestParam String booking_status) {

        List<Booking> booking = bookingService.get_by_renter_and_booking_status(renterID, booking_status);

        return getResponseEntityforsearchperson(booking_status, booking);

    }


    // return all owner bookings
    @GetMapping("/owner/{ownerID}")
    public ResponseEntity<?> get_all_owner_bookings(@PathVariable Integer ownerID) {

        List<Booking> booking = bookingService.get_all_owner_bookings(ownerID);

        if (booking.isEmpty()) {
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("code", NOT_FOUND.value());
            responseBody.put("data", "No booking records for ownerID " + ownerID);
            return ResponseEntity.status(NOT_FOUND).body(responseBody);
        }

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("code", OK.value());
        responseBody.put("data", booking);

        return ResponseEntity.ok(responseBody);

    }


    // return all renter bookings
    @GetMapping("/renter/{renterID}")
    public ResponseEntity<?> get_by_renter_and_booking_status (@PathVariable Integer renterID) {

        List<Booking> booking = bookingService.get_all_renter_bookings(renterID);

        if (booking.isEmpty()) {
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("code", NOT_FOUND.value());
            responseBody.put("data", "No booking records for renterID " + renterID);
            return ResponseEntity.status(NOT_FOUND).body(responseBody);
        }

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("code", OK.value());
        responseBody.put("data", booking);

        return ResponseEntity.ok(responseBody);

    }


    // update booking status
    @PutMapping("/{bookingID}")
    public ResponseEntity<?> update_booking_status(
            @PathVariable("bookingID") Integer bookingID,
            @RequestParam String booking_status) {

        boolean exits = bookingRepository.existsById(bookingID);

        if (!exits) {
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("code", NOT_FOUND.value());
            responseBody.put("data", "Booking ID " + bookingID + " not found");
            return ResponseEntity.status(NOT_FOUND).body(responseBody);
        }

        Booking updatedBooking = bookingService.update_booking_status(bookingID, booking_status);
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("code", OK.value());
        responseBody.put("data", updatedBooking);
        return ResponseEntity.ok(responseBody);
    }

}
