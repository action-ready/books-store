package com.example.book_store.controller;


import com.example.book_store.payload.request.AccountDTORequest;
import com.example.book_store.payload.request.LoginDTORequest;
import com.example.book_store.payload.response.LoginDTOResponse;
import com.example.book_store.payload.response.TokenDTOResponse;
import com.example.book_store.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<?> login(@RequestBody LoginDTORequest request) {


        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return ResponseEntity.ok(authService.login(request));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect username or password");
        }

    }

    @GetMapping("/refreshToken")
    public TokenDTOResponse refreshToken(@RequestParam(name = "refreshToken") String refreshToken) {
        return authService.getNewToken(refreshToken);
    }
}
