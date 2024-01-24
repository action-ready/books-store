package com.example.book_store.service;

import com.example.book_store.entity.Account;
import com.example.book_store.entity.Token;
import com.example.book_store.payload.response.TokenDTOResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;

public interface JWTService {

    String generateToken(String username);

    Authentication parseTokenToUserInformation(HttpServletRequest request);

    Token generateRefreshToken(Account account);

    TokenDTOResponse getNewToken(String refreshToken);

}
