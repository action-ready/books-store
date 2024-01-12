package com.example.book_store.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Account")
public class Account extends SuperEntity{

    private String fullName;
    private String username;
    private String password;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

    @OneToMany(mappedBy = "account")
    private List<ShoppingCart> shoppingCart;


    @OneToMany(mappedBy = "account")
    List<Customer> customers;
    enum Role {
        ADMIN,
        USER,
    }
    enum AccountStatus {
        ACTIVE,
        INACTIVE,
        PENDING,
        LOCKED
    }
}
