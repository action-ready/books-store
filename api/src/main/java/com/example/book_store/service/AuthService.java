package com.example.book_store.service;

import com.example.book_store.payload.request.AccountDTORequest;
import com.example.book_store.payload.request.LoginDTORequest;
import com.example.book_store.payload.response.LoginDTOResponse;
import com.example.book_store.payload.response.TokenDTOResponse;

public interface AuthService {

   void registerAccount(AccountDTORequest request);

   LoginDTOResponse login(LoginDTORequest request);

   TokenDTOResponse getNewToken(String refreshToken);
}
