package com.example.book_store.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class ShoppingCartRequest {

    private BigDecimal totalPrice;
    private Integer totalItem;
    private List<CartItem> cartItems;

    @Getter
    @Setter
    public static class CartItem {
        private Integer quantity;
        private Long bookId;
    }
}
