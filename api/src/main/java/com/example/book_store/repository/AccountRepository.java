package com.example.book_store.repository;

import com.example.book_store.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {


    Account findByUsername(String username);

    boolean existsByUsername(String username);


}
