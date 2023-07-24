package iam.phomenko.clothes.service;

import iam.phomenko.clothes.domain.payments.Payment;
import iam.phomenko.clothes.exception.DomainNotFoundException;

import javax.persistence.EntityNotFoundException;

public interface PaymentService {
    Payment getById(String id) throws EntityNotFoundException, DomainNotFoundException;
}
