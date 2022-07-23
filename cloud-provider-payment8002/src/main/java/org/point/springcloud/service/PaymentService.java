package org.point.springcloud.service;

import org.point.springcloud.entities.Payment;

public interface PaymentService {

    int create(Payment payment);

    Payment getPaymentById(Long id);
}
