package com.example.book_store.service.impl;


import com.example.book_store.entity.Account;
import com.example.book_store.service.AccountService;
import com.example.book_store.service.JWTService;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

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

    private final AccountService accountService;

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
}
