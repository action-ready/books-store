package com.example.book_store.service;


import com.example.book_store.payload.request.ShoppingCartRequest;
import com.example.book_store.payload.response.CartItemsDTOResponse;

import java.util.List;

public interface ShoppingCartService {

    void createShoppingCart(ShoppingCartRequest request, Long accountId);

    void increaseCartItemQuantity(Long cartItemId, int quantityToAdd);

    void decreaseCartItemQuantity(Long cartItemId, int quantityToSubtract);

    void deleteCart(Long cartId);

    List<CartItemsDTOResponse> getCartByAccountId(Long id);
}
