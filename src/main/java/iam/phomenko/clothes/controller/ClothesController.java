package iam.phomenko.clothes.controller;

import iam.phomenko.clothes.domain.clothes.Clothes;
import iam.phomenko.clothes.dto.clothes.CreateClothesDTO;
import iam.phomenko.clothes.dto.clothes.ClothesDTO;
import iam.phomenko.clothes.dto.pojo.ErrorDTO;
import iam.phomenko.clothes.dto.pojo.SuccessDTO;
import iam.phomenko.clothes.exception.CollectionDontExistException;
import iam.phomenko.clothes.exception.DomainNotFoundException;
import iam.phomenko.clothes.service.ClothesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/clothes")
public class ClothesController {
    private final ClothesService clothesService;

    @Autowired
    public ClothesController(ClothesService clothesService) {
        this.clothesService = clothesService;
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody CreateClothesDTO dto,
                                         Authentication authentication) {
        try {
            Clothes clothes = clothesService.create(dto, authentication);
            return ResponseEntity.ok().body(new SuccessDTO(clothes.getId()));
        } catch (Exception | CollectionDontExistException e) {
            return new ResponseEntity<>(new ErrorDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") String id) {
        try {
            return ResponseEntity.ok().body(new ClothesDTO(clothesService.getById(id)));
        } catch (Exception | DomainNotFoundException e) {
            return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
        }
    }
}
