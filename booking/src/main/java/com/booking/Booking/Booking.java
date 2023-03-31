package com.booking.Booking;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table
public class Booking {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookingID;

    @Column
    private Integer renterID;

    @Column
    private Integer ownerID;

    @Column
    private Integer propertyID;

    @Column
    private Integer paymentID;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(columnDefinition = "DATETIME")
    private LocalDateTime start_datetime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(columnDefinition = "DATETIME")
    private LocalDateTime end_datetime;

    @Column
    private String booking_status;


    // Constructor

    public Booking() {
    }

    public Booking(Integer bookingID, Integer renterID,
                   Integer ownerID, Integer propertyID,
                   Integer paymentID, LocalDateTime start_datetime,
                   LocalDateTime end_datetime, String booking_status) {
        this.bookingID = bookingID;
        this.renterID = renterID;
        this.ownerID = ownerID;
        this.propertyID = propertyID;
        this.paymentID = paymentID;
        this.start_datetime = start_datetime;
        this.end_datetime = end_datetime;
        this.booking_status = booking_status;
    }

    public Booking(Integer renterID, Integer ownerID,
                   Integer propertyID, Integer paymentID,
                   LocalDateTime start_datetime, LocalDateTime end_datetime,
                   String booking_status) {
        this.renterID = renterID;
        this.ownerID = ownerID;
        this.propertyID = propertyID;
        this.paymentID = paymentID;
        this.start_datetime = start_datetime;
        this.end_datetime = end_datetime;
        this.booking_status = booking_status;
    }

    // Getter and Setter


    public Integer getBookingID() {
        return bookingID;
    }

    public void setBookingID(Integer bookingID) {
        this.bookingID = bookingID;
    }

    public Integer getRenterID() {
        return renterID;
    }

    public void setRenterID(Integer renterID) {
        this.renterID = renterID;
    }

    public Integer getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(Integer ownerID) {
        this.ownerID = ownerID;
    }

    public Integer getPropertyID() {
        return propertyID;
    }

    public void setPropertyID(Integer propertyID) {
        this.propertyID = propertyID;
    }

    public Integer getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(Integer paymentID) {
        this.paymentID = paymentID;
    }

    public LocalDateTime getStart_datetime() {
        return start_datetime;
    }

    public void setStart_datetime(LocalDateTime start_datetime) {
        this.start_datetime = start_datetime;
    }

    public LocalDateTime getEnd_datetime() {
        return end_datetime;
    }

    public void setEnd_datetime(LocalDateTime end_datetime) {
        this.end_datetime = end_datetime;
    }

    public String getBooking_status() {
        return booking_status;
    }

    public void setBooking_status(String booking_status) {
        this.booking_status = booking_status;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingID=" + bookingID +
                ", renterID=" + renterID +
                ", ownerID=" + ownerID +
                ", propertyID=" + propertyID +
                ", paymentID=" + paymentID +
                ", start_datetime=" + start_datetime +
                ", end_datetime=" + end_datetime +
                ", booking_status='" + booking_status + '\'' +
                '}';
    }


}


