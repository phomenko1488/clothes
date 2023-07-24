package iam.phomenko.clothes.controller;

import iam.phomenko.clothes.dto.payout.PayoutCreateDTO;
import iam.phomenko.clothes.dto.pojo.ErrorDTO;
import iam.phomenko.clothes.exception.DomainNotFoundException;
import iam.phomenko.clothes.exception.NoSuchMoneyException;
import iam.phomenko.clothes.service.PayoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.CredentialExpiredException;

@RestController
@RequestMapping("/api/v1/payouts")
public class PayoutController {
    private final PayoutService payoutService;

    @Autowired
    public PayoutController(PayoutService payoutService) {
        this.payoutService = payoutService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") String id) {
        try {

            return ResponseEntity.ok().body(payoutService.getById(id));
        } catch (Exception | DomainNotFoundException e) {
            return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody PayoutCreateDTO dto,
                                         Authentication authentication) {
        try {
            return ResponseEntity.ok().body(payoutService.create(dto, authentication));
        } catch (CredentialExpiredException | NoSuchMoneyException e) {
            return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
        }
    }
}
