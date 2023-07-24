package iam.phomenko.clothes.service.impl;

import iam.phomenko.clothes.domain.payments.Payout;
import iam.phomenko.clothes.domain.users.User;
import iam.phomenko.clothes.dto.payout.PayoutCreateDTO;
import iam.phomenko.clothes.enums.PaymentRequestStatus;
import iam.phomenko.clothes.exception.DomainNotFoundException;
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
    public Payout getById(String id) throws DomainNotFoundException {
        Payout payout = repository.getPayoutById(id);
        if (payout==null)
            throw new DomainNotFoundException("Payout with such id doesn't exists");
        return payout;
    }

    @Override
    public Payout create(PayoutCreateDTO dto, Authentication authentication) throws CredentialExpiredException, NoSuchMoneyException {
        User creator = userService.getUserByAuthentication(authentication);
        if (creator == null)
            throw new CredentialExpiredException("Your session was expired");
        if (creator.getBalance().floatValue() < dto.getAmount().floatValue())
            throw new NoSuchMoneyException("You have not enough money");
        Payout payout = new Payout();
        payout.setId(generator.generateId());
        payout.setAmount(dto.getAmount());
        payout.setDestination(dto.getDestination().trim());
        payout.setStatus(PaymentRequestStatus.CREATED);
        payout.setCreationDate(Date.from(Instant.now()));
        creator.setBalance(BigDecimal.valueOf(creator.getBalance().floatValue() - payout.getAmount().floatValue()));
        creator.setBlockedBalance(BigDecimal.valueOf(creator.getBlockedBalance().floatValue() + payout.getAmount().floatValue()));
        userService.save(creator);
        return repository.save(payout);
    }
}
