package iam.phomenko.clothes.controller;

import iam.phomenko.clothes.domain.payments.Payment;
import iam.phomenko.clothes.dto.payment.PaymentDTO;
import iam.phomenko.clothes.dto.pojo.ErrorDTO;
import iam.phomenko.clothes.exception.DomainNotFoundException;
import iam.phomenko.clothes.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1/payments")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") String id) {
        try {
            return ResponseEntity.ok().body(new PaymentDTO(paymentService.getById(id)));
        } catch (Exception | DomainNotFoundException e) {
            return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
        }
    }
}
