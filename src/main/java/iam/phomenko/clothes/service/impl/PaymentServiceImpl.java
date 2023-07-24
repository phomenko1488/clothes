package iam.phomenko.clothes.service.impl;

import iam.phomenko.clothes.domain.payments.Payment;
import iam.phomenko.clothes.dto.payout.PayoutCreateDTO;
import iam.phomenko.clothes.exception.DomainNotFoundException;
import iam.phomenko.clothes.repository.PaymentRepository;
import iam.phomenko.clothes.service.PaymentService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository repository;

    public PaymentServiceImpl(PaymentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Payment getById(String id) throws DomainNotFoundException {
        Payment payment = repository.getPaymentById(id);
        if(payment==null)
            throw new DomainNotFoundException("Payment with such id doesn't exists");
        return payment;
    }

}
