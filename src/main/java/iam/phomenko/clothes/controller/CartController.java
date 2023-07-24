package iam.phomenko.clothes.controller;

import iam.phomenko.clothes.dto.cart.AddItemToCartDTO;
import iam.phomenko.clothes.dto.cart.DeleteCartItemDTO;
import iam.phomenko.clothes.dto.cart.EditCartItemDTO;
import iam.phomenko.clothes.dto.pojo.ErrorDTO;
import iam.phomenko.clothes.dto.pojo.SuccessDTO;
import iam.phomenko.clothes.exception.DomainNotFoundException;
import iam.phomenko.clothes.service.CartService;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.session.Session;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("/api/v1/cart")
@Log
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<Object> getCard(HttpSession session) {
        return ResponseEntity.ok().body(new SuccessDTO(cartService.mapToDTO(cartService.getCart(session))));
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody AddItemToCartDTO addItemToCart,
                                      HttpSession session) {
        try {
            return ResponseEntity.ok().body(cartService.mapToDTO(cartService.add(addItemToCart, session)));
        } catch (DomainNotFoundException e) {
            return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
        }
    }

    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody DeleteCartItemDTO deleteCartItemDTO,
                                         HttpSession session) {
        try {
            return ResponseEntity.ok().body(cartService.mapToDTO(cartService.deleteItem(deleteCartItemDTO, session)));
        } catch (DomainNotFoundException e) {
            return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<Object> edit(@RequestBody EditCartItemDTO editCartItemDTO,
                                       HttpSession session) {
        try {
            return ResponseEntity.ok().body(cartService.mapToDTO(cartService.edit(editCartItemDTO, session)));
        } catch (DomainNotFoundException e) {
            return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
        }
    }

    @PostMapping("/clear")
    public ResponseEntity<Object> clear(HttpSession session) {
        return ResponseEntity.ok().body(cartService.clear(session));
    }
}
