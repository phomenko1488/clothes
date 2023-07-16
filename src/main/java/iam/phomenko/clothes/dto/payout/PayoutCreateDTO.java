package iam.phomenko.clothes.dto.payout;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayoutCreateDTO {
    private String userId;
    private BigDecimal amount;
    private String destination;

}
