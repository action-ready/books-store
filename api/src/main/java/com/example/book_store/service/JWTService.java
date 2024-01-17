package com.example.book_store.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;

public interface JWTService {

    String generateToken(String username);

    Authentication parseTokenToUserInformation(HttpServletRequest request);


}
