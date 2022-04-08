package com.bartoszek.lab7.DAO;

import com.bartoszek.lab7.DTO.Client;
import com.bartoszek.lab7.DTO.Payment;

import java.util.Set;

public interface PaymentDAO {
    Payment getPayment(Integer paymentID);
    Set<Payment> getAllPayments();
    boolean insertPayment(Payment payment);
    boolean updatePayment(Payment payment);
    boolean removePayment(Integer paymentID);
}
