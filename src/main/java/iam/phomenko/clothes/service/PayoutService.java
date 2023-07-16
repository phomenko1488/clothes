package iam.phomenko.clothes.service;

import iam.phomenko.clothes.domain.payments.Payout;
import iam.phomenko.clothes.dto.payout.PayoutCreateDTO;
import iam.phomenko.clothes.exception.NoSuchMoneyException;
import org.springframework.security.core.Authentication;

import javax.persistence.EntityNotFoundException;
import javax.security.auth.login.CredentialExpiredException;
import java.math.BigDecimal;

public interface PayoutService {
    Payout getById(String id) throws EntityNotFoundException;

    Payout create(PayoutCreateDTO dto, Authentication authentication) throws CredentialExpiredException, NoSuchMoneyException;;
}
