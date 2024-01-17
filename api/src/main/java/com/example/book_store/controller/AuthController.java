package com.example.book_store.controller;


import com.example.book_store.payload.request.AccountDTORequest;
import com.example.book_store.payload.request.LoginDTORequest;
import com.example.book_store.payload.response.LoginDTOResponse;
import com.example.book_store.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.AuthenticationManager;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<String> registerAccount(@RequestBody @Valid AccountDTORequest request) {

        authService.registerAccount(request);
        return ResponseEntity.ok("Register successfully");
    }


    @PostMapping("/login")
    public ResponseEntity<LoginDTOResponse> login(@RequestBody LoginDTORequest request) {


        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return ResponseEntity.ok(authService.login(request));

    }
}
