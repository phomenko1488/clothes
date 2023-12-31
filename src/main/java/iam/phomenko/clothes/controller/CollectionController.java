package iam.phomenko.clothes.controller;

import iam.phomenko.clothes.dto.collection.CollectionCreateDTO;
import iam.phomenko.clothes.dto.collection.CollectionDTO;
import iam.phomenko.clothes.dto.pojo.ErrorDTO;
import iam.phomenko.clothes.dto.pojo.SuccessDTO;
import iam.phomenko.clothes.exception.DomainNotFoundException;
import iam.phomenko.clothes.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.CredentialExpiredException;

@RestController
@RequestMapping("/api/v1/collections")
public class CollectionController {
    private final CollectionService collectionService;

    @Autowired
    public CollectionController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody CollectionCreateDTO dto,
                                         Authentication authentication) {
        try {
            return ResponseEntity.ok().body(new SuccessDTO(collectionService.create(dto, authentication).getId()));
        } catch (CredentialExpiredException e) {
            return new ResponseEntity<>(new ErrorDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") String id) {
        try {
            return ResponseEntity.ok().body(new CollectionDTO(collectionService.getById(id)));
        } catch (Exception | DomainNotFoundException e) {
            return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
        }
    }
}
