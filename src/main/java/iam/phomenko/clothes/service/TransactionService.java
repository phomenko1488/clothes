package iam.phomenko.clothes.service;

import iam.phomenko.clothes.domain.payments.Transaction;
import iam.phomenko.clothes.exception.DomainNotFoundException;

import javax.persistence.EntityNotFoundException;

public interface TransactionService {
    Transaction getById(String id) throws EntityNotFoundException, DomainNotFoundException;
}
