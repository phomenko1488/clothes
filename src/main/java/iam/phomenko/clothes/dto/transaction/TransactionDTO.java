package iam.phomenko.clothes.dto.transaction;

import iam.phomenko.clothes.domain.payments.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {
    private String id;
    private BigDecimal amount;
    private Date time;

    public TransactionDTO(Transaction transaction) {
        this.id = transaction.getId();
        this.amount = transaction.getAmount();
        this.time = transaction.getTime();
    }
}
