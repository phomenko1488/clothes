package iam.phomenko.clothes.controller;

import iam.phomenko.clothes.dto.pojo.ErrorDTO;
import iam.phomenko.clothes.dto.transaction.TransactionDTO;
import iam.phomenko.clothes.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") String id) {
        try {
            return ResponseEntity.ok().body(new TransactionDTO(transactionService.getById(id)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
        }
    }
}
