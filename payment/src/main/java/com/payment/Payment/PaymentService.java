package com.payment.Payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    // get all owners
    public List<Payment> get_all_payments(){
        return paymentRepository.findAll();
    }

    // get owner by ownerID

    public Optional get_payment_by_paymentID(Integer paymentID) {

        boolean exits = paymentRepository.existsById(paymentID);

        if (!exits) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Payment with paymentID " + paymentID + "does not exist");
        }

        return paymentRepository.findById(paymentID);
    }
}
