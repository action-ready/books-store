package com.example.book_store.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Customer")
public class Customer extends SuperEntity{

    private String fullName;
    private String address;
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
