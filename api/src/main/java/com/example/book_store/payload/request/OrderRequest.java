package com.example.book_store.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class OrderRequest {

    private BigDecimal totalPrice;

    private Customer customer;
    @NotBlank
    private String payment;



    @Getter
    @Setter
    public static class Customer {
        @NotBlank
        private String fullName;
        @NotBlank
        private String address;
        @NotBlank
        private String phoneNumber;
    }
}
