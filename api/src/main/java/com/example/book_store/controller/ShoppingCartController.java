package com.example.book_store.controller;


import com.example.book_store.payload.request.ShoppingCartRequest;
import com.example.book_store.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    @PostMapping("/account/{accountId}/shopping-carts")
    public ResponseEntity<String> createShoppingCart(@RequestBody ShoppingCartRequest request, @PathVariable Long accountId) {
        shoppingCartService.createShoppingCart(request, accountId);
        return ResponseEntity.ok("Create success");
    }

    @PostMapping("/shopping-carts/cart-item/{cartItemId}/increase")
    public ResponseEntity<String> increaseCartItemQuantity(@PathVariable Long cartItemId,
                                                           @RequestParam int quantityToAdd) {
        shoppingCartService.increaseCartItemQuantity(cartItemId, quantityToAdd);

        return ResponseEntity.ok("Quantity increased successfully");
    }

    @PostMapping("/shopping-carts/cart-item/{cartItemId}/decrease")
    public ResponseEntity<String> decreaseCartItemQuantity(@PathVariable Long cartItemId,
                                                           @RequestParam int quantityToSubtract) {
        shoppingCartService.decreaseCartItemQuantity(cartItemId, quantityToSubtract);

        return ResponseEntity.ok("Quantity decreased successfully");
    }

    @DeleteMapping("/shopping-carts/{cartId}")
    public ResponseEntity<String> decreaseCartItemQuantity(@PathVariable Long cartId
                                                           ) {
        shoppingCartService.deleteCart(cartId);

        return ResponseEntity.ok("Quantity decreased successfully");
    }

}
