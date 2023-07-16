package iam.phomenko.clothes.service;

import iam.phomenko.clothes.domain.payments.Payout;
import iam.phomenko.clothes.exception.NoSuchMoneyException;
import org.springframework.security.core.Authentication;

import javax.security.auth.login.CredentialExpiredException;
import java.math.BigDecimal;

public interface PayoutService {
    Payout getById(String id);
    Payout create(Authentication authentication, BigDecimal amount, String destination) throws CredentialExpiredException, NoSuchMoneyException;
}
