package iam.phomenko.clothes.dto.payment;

import iam.phomenko.clothes.domain.payments.Payment;
import iam.phomenko.clothes.dto.transaction.TransactionDTO;
import org.springframework.http.ResponseEntity;

public class PaymentDTO {
    private TransactionDTO transaction;
    public PaymentDTO(Payment payment) {
        this.transaction= new TransactionDTO(payment.getTransaction());
    }
}
