package iam.phomenko.clothes.service;

import iam.phomenko.clothes.domain.cart.CartItem;
import iam.phomenko.clothes.dto.cart.*;
import iam.phomenko.clothes.exception.DomainNotFoundException;
import javax.servlet.http.HttpSession;
import java.util.List;

public interface CartService {

    List<CartItem> changeCount(ChangeCartCountDTO dto, HttpSession session, Long newSize) throws DomainNotFoundException;

    List<CartItem> add(AddItemToCartDTO addItemToCart, HttpSession session) throws DomainNotFoundException;
    List<CartItem> getCart(HttpSession session);
    List<CartItemDTO> mapToDTO(List<CartItem> items);

    List<CartItem> deleteItem(DeleteCartItemDTO deleteCartItemDTO, HttpSession session) throws DomainNotFoundException;

    List<CartItem> edit(EditCartItemDTO editCartItemDTO, HttpSession session) throws DomainNotFoundException;

    List<CartItem> clear(HttpSession session);
}
