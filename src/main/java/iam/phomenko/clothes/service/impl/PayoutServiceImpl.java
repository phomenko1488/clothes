package iam.phomenko.clothes.service.impl;

import iam.phomenko.clothes.domain.payments.Payout;
import iam.phomenko.clothes.domain.users.User;
import iam.phomenko.clothes.enums.PaymentRequestStatus;
import iam.phomenko.clothes.exception.NoSuchMoneyException;
import iam.phomenko.clothes.repository.PayoutRepository;
import iam.phomenko.clothes.service.PayoutService;
import iam.phomenko.clothes.service.UserService;
import iam.phomenko.clothes.utils.Generator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.security.auth.login.CredentialExpiredException;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;

@Service
public class PayoutServiceImpl implements PayoutService {
    private final PayoutRepository repository;
    private final UserService userService;
    private final Generator generator;

    @Autowired
    public PayoutServiceImpl(PayoutRepository repository, UserService userService, Generator generator) {
        this.repository = repository;
        this.userService = userService;
        this.generator = generator;
    }

    @Override
    public Payout getById(String  id) {
        return repository.getPayoutById(id);
    }

    @Override
    public Payout create(Authentication authentication, BigDecimal amount, String destination) throws CredentialExpiredException, NoSuchMoneyException {
        User creator = userService.getUserByAuthentication(authentication);
        if (creator == null)
            throw new CredentialExpiredException("");
        if (creator.getBalance().floatValue()<amount.floatValue())
            throw new NoSuchMoneyException();
        Payout payout = new Payout();
        payout.setAmount(amount);
        payout.setId(generator.generateId());
        payout.setDestination(destination);
        payout.setStatus(PaymentRequestStatus.CREATED);
        payout.setCreationDate(Date.from(Instant.now()));
        return payout;
    }
}
