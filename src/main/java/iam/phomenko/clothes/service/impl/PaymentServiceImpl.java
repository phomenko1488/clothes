package iam.phomenko.clothes.service.impl;

import iam.phomenko.clothes.domain.payments.Payment;
import iam.phomenko.clothes.repository.PaymentRepository;
import iam.phomenko.clothes.service.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository repository;

    public PaymentServiceImpl(PaymentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Payment getById(String id) {
        return repository.getPaymentById(id);
    }
}
