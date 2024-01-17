package com.example.book_store.payload.request;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTORequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
