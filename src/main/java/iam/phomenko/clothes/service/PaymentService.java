package iam.phomenko.clothes.service;

import iam.phomenko.clothes.domain.payments.Payment;
import iam.phomenko.clothes.dto.payout.PayoutCreateDTO;
import org.springframework.security.core.Authentication;

import javax.persistence.EntityNotFoundException;

public interface PaymentService {
    Payment getById(String id) throws EntityNotFoundException;

    Object create(PayoutCreateDTO dto, Authentication authentication);
}
