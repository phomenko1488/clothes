package iam.phomenko.clothes.service;

import iam.phomenko.clothes.domain.payments.Payment;

public interface PaymentService {
    Payment getById(String id);
}
