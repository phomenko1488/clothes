package iam.phomenko.clothes.domain.payments;

import iam.phomenko.clothes.enums.PaymentRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "payouts")
public class Payout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date creationDate;

    @Enumerated(value = EnumType.STRING)
    private PaymentRequestStatus status;

    private BigDecimal amount;

    private String destination;
}
