package com.booking.Booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    @Query("SELECT distinct b.propertyID FROM Booking b WHERE b.booking_status IN :status_list AND b.end_datetime >= :start_datetime AND b.start_datetime <= :end_datetime")
    List<Integer> find_Booking_by_Status_and_DateTime(List <String> status_list, @Param("start_datetime") LocalDateTime start_datetime, @Param("end_datetime") LocalDateTime end_datetime);

    @Query("SELECT b FROM Booking b WHERE b.booking_status = :booking_status AND b.ownerID = :ownerID")
    List<Booking> get_by_owner_and_booking_status(@Param("ownerID") Integer ownerID, @Param("booking_status") String booking_status);

    @Query("SELECT b FROM Booking b WHERE b.booking_status = :booking_status AND b.renterID = :renterID")
    List<Booking> get_by_renter_and_booking_status(@Param("renterID") Integer renterID, @Param("booking_status") String booking_status);

    @Query("SELECT b FROM Booking b WHERE b.ownerID = :ownerID")
    List<Booking> get_all_owner_bookings(@PathVariable("ownerID") Integer ownerID);

    @Query("SELECT b FROM Booking b WHERE b.renterID = :renterID")
    List<Booking> get_all_renter_bookings(@PathVariable("renterID") Integer renterID);

}
