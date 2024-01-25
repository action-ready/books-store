package com.example.book_store.payload.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderFilterRequest {

    private String status;
    private String phoneNumber;
}
