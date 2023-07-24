package iam.phomenko.clothes.service.impl;

import iam.phomenko.clothes.domain.cart.CartItem;
import iam.phomenko.clothes.domain.clothes.Clothes;
import iam.phomenko.clothes.dto.cart.*;
import iam.phomenko.clothes.exception.DomainNotFoundException;
import iam.phomenko.clothes.service.CartService;
import iam.phomenko.clothes.service.ClothesService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {
    private final ClothesService clothesService;

    public CartServiceImpl(ClothesService clothesService) {
        this.clothesService = clothesService;
    }

    @Override
    public List<CartItem> getCart(HttpSession session) {
        if (session == null || session.isNew())
            return new ArrayList<>();
        return (List<CartItem>) session.getAttribute("cart");
    }

    private void update(List<CartItem> items, HttpSession session) {
        session.setAttribute("cart", items);
    }

    @Override
    public List<CartItem> clear(HttpSession session) {
        ArrayList<CartItem> result = new ArrayList<>();
        session.setAttribute("cart", result);
        return result;
    }

    @Override
    public List<CartItemDTO> mapToDTO(List<CartItem> items) {
        return items.stream().map(CartItemDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<CartItem> deleteItem(DeleteCartItemDTO deleteCartItemDTO, HttpSession session) throws DomainNotFoundException {
        List<CartItem> cart = getCart(session);
        Clothes item = clothesService.getById(deleteCartItemDTO.getItemId());
        CartItem cartItem = cart.stream().filter(cartItem1 -> cartItem1.getSize() == deleteCartItemDTO.getSize() && cartItem1.getClothes().getId().equals(item.getId())).findFirst().orElse(null);
        if (cartItem == null)
            throw new DomainNotFoundException("Unknown clothes");
        cart.remove(cartItem);
        update(cart, session);
        return cart;
    }

    @Override
    public List<CartItem> edit(EditCartItemDTO editCartItemDTO, HttpSession session) {
        List<CartItem> cart = getCart(session);
        CartItem cartItem;
        if (editCartItemDTO.getIsSizeEdited())
            cartItem = cart.stream().filter(item -> item.getCount().equals(editCartItemDTO.getAmount()) && item.getClothes().getId().equals(editCartItemDTO.getItemId())).findFirst().orElse(null);
        else
            cartItem = cart.stream().filter(item -> item.getSize() == editCartItemDTO.getSize() && item.getClothes().getId().equals(editCartItemDTO.getItemId())).findFirst().orElse(null);
        if (cartItem != null) {
            System.out.println(cartItem);
            cart.remove(cartItem);
            cartItem.setSize(editCartItemDTO.getSize());
            cartItem.setCount(editCartItemDTO.getAmount());
            cart.add(cartItem);
        }
        update(cart, session);
        return cart;
    }

    @Override
    public List<CartItem> changeCount(ChangeCartCountDTO dto, HttpSession session, Long newSize) throws DomainNotFoundException {
        List<CartItem> cart = getCart(session);
        CartItem item = cart.stream().filter(cartItem -> cartItem.getClothes().getId().equals(dto.getClothesId()) && cartItem.getSize().equals(dto.getSize())).findFirst().orElse(null);
        if (item == null)
            return add(new AddItemToCartDTO(dto.getClothesId(), dto.getSize(), dto.getNewCount()), session);
        cart.remove(item);
        item.setCount(dto.getNewCount());
        cart.add(item);
        update(cart, session);
        return cart;
    }

    @Override
    public List<CartItem> add(AddItemToCartDTO addItemToCart, HttpSession session) throws DomainNotFoundException {
        List<CartItem> cart = getCart(session);
        CartItem item = cart.stream().filter(cartItem -> cartItem.getClothes().getId().equals(addItemToCart.getItemId()) && cartItem.getSize().equals(addItemToCart.getSize())).findFirst().orElse(null);
        if (item != null)
            cart.remove(item);
        cart.add(new CartItem(clothesService.getById(addItemToCart.getItemId()), addItemToCart.getAmount(), addItemToCart.getSize()));
        update(cart, session);
        return cart;
    }
}
