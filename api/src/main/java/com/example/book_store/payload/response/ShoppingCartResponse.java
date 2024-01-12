package com.example.book_store.payload.response;

import com.example.book_store.entity.Account;
import com.example.book_store.entity.CartItem;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@Builder
public class ShoppingCartResponse {

    private Long id;
    private BigDecimal totalPrice;
    private Integer totalItem;
    private Long accountId;

    private Set<CartItem> cartItems;


    @Getter
    @Setter
    static class CartItem {
        private Long id;
        private Integer quantity;
        private Long bookId;
    }
}
