package com.example.book_store.service;


import com.example.book_store.payload.request.ShoppingCartRequest;

public interface ShoppingCartService {

    void createShoppingCart(ShoppingCartRequest request, Long accountId);

    void increaseCartItemQuantity(Long cartItemId, int quantityToAdd);

    void decreaseCartItemQuantity(Long cartItemId, int quantityToSubtract);

    void deleteCart(Long cartId);
}
