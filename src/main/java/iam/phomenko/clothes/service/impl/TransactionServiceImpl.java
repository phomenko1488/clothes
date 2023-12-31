package iam.phomenko.clothes.service.impl;

import iam.phomenko.clothes.domain.payments.Transaction;
import iam.phomenko.clothes.exception.DomainNotFoundException;
import iam.phomenko.clothes.repository.TransactionRepository;
import iam.phomenko.clothes.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository repository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Transaction getById(String id) throws DomainNotFoundException {

        Transaction transaction = repository.getTransactionById(id);
        if (transaction==null)
            throw new DomainNotFoundException("Transaction with such id doesn't exists");
        return transaction;
    }
}
