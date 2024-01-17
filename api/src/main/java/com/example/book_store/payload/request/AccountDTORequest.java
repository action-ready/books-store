package com.example.book_store.payload.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDTORequest {

    private String fullName;
    private String username;
    private String password;
    private String phoneNumber;
    private String role;
    private String accountStatus;
}
