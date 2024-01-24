package com.example.book_store.service.impl;


import com.example.book_store.entity.Account;
import com.example.book_store.entity.Token;
import com.example.book_store.payload.request.AccountDTORequest;
import com.example.book_store.payload.request.LoginDTORequest;
import com.example.book_store.payload.response.LoginDTOResponse;
import com.example.book_store.payload.response.TokenDTOResponse;
import com.example.book_store.repository.AccountRepository;
import com.example.book_store.service.AuthService;
import com.example.book_store.service.JWTService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    @Override
    @Transactional
    public void registerAccount(AccountDTORequest request) {
        Account account = modelMapper.map(request, Account.class);

        if (accountRepository.existsByUsername(request.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username is exits");
        }
        account.setPassword(passwordEncoder.encode(request.getPassword()));
        account.setAccountStatus(Account.AccountStatus.PENDING);
        accountRepository.save(account);
    }

    @Override
    public LoginDTOResponse login(LoginDTORequest request) {

        Account account = accountRepository.findByUsername(request.getUsername());



        String token = jwtService.generateToken(account.getUsername());

        Token refreshToken = jwtService.generateRefreshToken(account);

        LoginDTOResponse loginDTOResponse = new LoginDTOResponse();
        loginDTOResponse.setId(account.getId());
        loginDTOResponse.setRole(account.getRole().toString());
        loginDTOResponse.setPhoneNumber(account.getPhoneNumber());
        loginDTOResponse.setUsername(account.getUsername());
        loginDTOResponse.setToken(token);
        loginDTOResponse.setRefreshToken(refreshToken.getKey().toString());
        return loginDTOResponse;
    }

    @Override
    public TokenDTOResponse getNewToken(String refreshToken) {
        return jwtService.getNewToken(refreshToken);
    }

}