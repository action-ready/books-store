package com.example.book_store.repository;

import com.example.book_store.entity.Account;
import com.example.book_store.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {

    void deleteTokenByAccount(Account account);

    Token findByKeyAndType(String refreshToken, Token.Type type);
}
