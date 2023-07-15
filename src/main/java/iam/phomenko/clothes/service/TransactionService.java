package iam.phomenko.clothes.service;

import iam.phomenko.clothes.domain.payments.Transaction;

public interface TransactionService {
    Transaction getById(Long id);
}
