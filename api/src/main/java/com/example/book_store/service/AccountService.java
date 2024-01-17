package com.example.book_store.service;

import com.example.book_store.entity.Account;
import org.springframework.security.core.userdetails.UserDetails;

public interface AccountService {
    UserDetails loadUserByUsername(String username);

    Account getAccountByUsername(String username);
}
