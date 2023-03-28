package com.payment.Payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    // find by oderID
    Optional<Payment> findByOrderID(String orderID);

    // find by AuthorizationID
    Optional<Payment> findByAuthorizationID(String authorizationID);

    // find by captureID
    Optional<Payment> findByCaptureID(String captureID);

    // find by paymentID
    Optional<Payment> findByPaymentID(Integer paymentID);

}
