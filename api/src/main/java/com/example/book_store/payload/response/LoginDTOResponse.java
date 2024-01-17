package com.example.book_store.payload.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTOResponse {

    private Long id;
    private String username;
    private String phoneNumber;
    private String role;
    private String token;
    private String refreshToken;
}
