package com.bartoszek.dao;

import java.util.Set;

import com.bartoszek.dto.Payment;

public interface PaymentDAO {
    Payment getPayment(Integer paymentID);
    Set<Payment> getAllPayments();
    boolean insertPayment(Payment payment);
    boolean updatePayment(Payment payment);
    boolean removePayment(Integer paymentID);
}
