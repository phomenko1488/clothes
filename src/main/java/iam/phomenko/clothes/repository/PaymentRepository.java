package iam.phomenko.clothes.repository;

import iam.phomenko.clothes.domain.payments.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, String> {
    Payment getPaymentById(String id);
}
