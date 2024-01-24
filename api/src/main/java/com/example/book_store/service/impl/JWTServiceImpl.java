package com.example.book_store.service.impl;


import com.example.book_store.entity.Account;
import com.example.book_store.entity.Token;
import com.example.book_store.payload.response.TokenDTOResponse;
import com.example.book_store.repository.TokenRepository;
import com.example.book_store.service.AccountService;
import com.example.book_store.service.JWTService;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JWTServiceImpl implements JWTService {

    @Value("${jwt.token.time.expiration}")
    private Long EXPIRATION_TIME;

    @Value("${jwt.token.secret}")
    private String SECRET;

    @Value("${jwt.token.prefix}")
    private String TOKEN_PREFIX;

    @Value("${jwt.token.header.authorization}")
    private String TOKEN_AUTHORIZATION;

    @Value("${jwt.token.REFRESH_EXPIRATION_TIME}")
    private long REFRESH_EXPIRATION_TIME;

    private final AccountService accountService;
    private final TokenRepository tokenRepository;

    @Override
    public String generateToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

        Claims claims = Jwts.claims().setSubject(username);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    @Override
    public Authentication parseTokenToUserInformation(HttpServletRequest request) {
        try {
            String tokenValue = request.getHeader(TOKEN_AUTHORIZATION);
            if (tokenValue != null) {
                tokenValue = tokenValue.replace(TOKEN_PREFIX, "");
            }
            String username = null;
            if (tokenValue != null && !tokenValue.isEmpty()) {
                username = Jwts.parser()
                        .setSigningKey(SECRET)
                        .parseClaimsJws(tokenValue)
                        .getBody()
                        .getSubject();
            }
            Account account = accountService.getAccountByUsername(username);
            if (account == null) {
                return null;
            }
            return username != null ?
                    new UsernamePasswordAuthenticationToken(
                            account.getUsername(),
                            null,
                            AuthorityUtils.createAuthorityList(account.getRole().toString())
                    ) : null;
        } catch (JwtException e) {
            return null;
        }
    }

    @Override
    @Transactional
    public Token generateRefreshToken(Account account) {
        Token token = Token.builder()
                .account(account)
                .type(Token.Type.REFRESH_TOKEN)
                .key(UUID.randomUUID().toString())
                .expiredDate(new Date(new Date().getTime() + REFRESH_EXPIRATION_TIME))
                .build();

        tokenRepository.deleteTokenByAccount(account);
        return tokenRepository.save(token);
    }

    @Override
    @Transactional
    public TokenDTOResponse getNewToken(String refreshToken) {
        Token oldRefreshToken = tokenRepository.findByKeyAndType(refreshToken, Token.Type.REFRESH_TOKEN);
        if (oldRefreshToken == null || oldRefreshToken.getExpiredDate().before(new Date())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token");
        }
        tokenRepository.deleteTokenByAccount(oldRefreshToken.getAccount());

        Token newRefreshToken = generateRefreshToken(oldRefreshToken.getAccount());

        String newToken = generateToken(oldRefreshToken.getAccount().getUsername());

        return TokenDTOResponse.builder()
                .refreshToken(newRefreshToken.getKey())
                .token(newToken)
                .build();
    }
}
