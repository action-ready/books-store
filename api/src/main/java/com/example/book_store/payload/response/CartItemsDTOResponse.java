package com.example.book_store.payload.response;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class CartItemsDTOResponse {

    private Long id;
    private int quantity;
    private Book book;
    private BigDecimal totalPriceCart;
    private BigDecimal totalPrice;

    @Getter
    @Setter
    static class Book {
        private Long id;
        private String name;
        private BigDecimal sellingPrice;
        private Long quantity;
        private List<String> bookImageUrl;
    }

    public BigDecimal getTotalPrice() {
        BigDecimal quantityBigDecimal = BigDecimal.valueOf(this.quantity); // Convert quantity to BigDecimal
        return quantityBigDecimal.multiply(this.book.getSellingPrice());
    }

}
