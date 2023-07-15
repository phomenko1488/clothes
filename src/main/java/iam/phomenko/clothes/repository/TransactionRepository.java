package iam.phomenko.clothes.repository;

import iam.phomenko.clothes.domain.payments.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Transaction getTransactionById(Long id);
}
