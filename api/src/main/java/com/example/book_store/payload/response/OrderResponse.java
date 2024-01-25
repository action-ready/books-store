package com.example.book_store.payload.response;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class OrderResponse {

    private Long id;
    private String orderCode;
    private String status;
    private BigDecimal totalPrice;
    private String accountFullName;

    public List<OrderDetails> orderDetails;


    @Getter
    @Setter
    static class OrderDetails {
        private Long id;
        private BigDecimal totalPrice;
        private Integer totalItem;
        private String payment;
        private String bookName;
        private BigDecimal bookSellingPrice;
        private String customerFullName;
    }
}
