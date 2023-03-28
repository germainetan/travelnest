package com.payment.Payment;

import jakarta.persistence.*;

@Entity
@Table
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paymentID;
    private String orderID;
    private String authorizationID;
    private String captureID;
    private String time_stamp;
    private String payment_status;
    private String payment_desc;
    private String refundID;


    // Constructor

    public Payment() {
    }

    public Payment(Integer paymentID, String orderID,
                   String authorizationID, String captureID,
                   String time_stamp, String payment_status,
                   String payment_desc, String refundID) {
        this.paymentID = paymentID;
        this.orderID = orderID;
        this.authorizationID = authorizationID;
        this.captureID = captureID;
        this.time_stamp = time_stamp;
        this.payment_status = payment_status;
        this.payment_desc = payment_desc;
        this.refundID = refundID;
    }

    public Payment(String orderID, String authorizationID,
                   String captureID, String time_stamp,
                   String payment_status, String payment_desc,
                   String refundID) {
        this.orderID = orderID;
        this.authorizationID = authorizationID;
        this.captureID = captureID;
        this.time_stamp = time_stamp;
        this.payment_status = payment_status;
        this.payment_desc = payment_desc;
        this.refundID = refundID;
    }

    // Getter and Setter


    public Integer getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(Integer paymentID) {
        this.paymentID = paymentID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getAuthorizationID() {
        return authorizationID;
    }

    public void setAuthorizationID(String authorizationID) {
        this.authorizationID = authorizationID;
    }

    public String getCaptureID() {
        return captureID;
    }

    public void setCaptureID(String captureID) {
        this.captureID = captureID;
    }

    public String getTime_stamp() {
        return time_stamp;
    }

    public void setTime_stamp(String time_stamp) {
        this.time_stamp = time_stamp;
    }

    public String getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(String payment_status) {
        this.payment_status = payment_status;
    }

    public String getPayment_desc() {
        return payment_desc;
    }

    public void setPayment_desc(String payment_desc) {
        this.payment_desc = payment_desc;
    }

    public String getRefundID() {
        return refundID;
    }

    public void setRefundID(String refundID) {
        this.refundID = refundID;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentID=" + paymentID +
                ", orderID='" + orderID + '\'' +
                ", authorizationID='" + authorizationID + '\'' +
                ", captureID='" + captureID + '\'' +
                ", time_stamp='" + time_stamp + '\'' +
                ", payment_status='" + payment_status + '\'' +
                ", payment_desc='" + payment_desc + '\'' +
                ", refundID='" + refundID + '\'' +
                '}';
    }
}
