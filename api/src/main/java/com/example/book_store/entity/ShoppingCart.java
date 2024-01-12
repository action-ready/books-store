package com.example.book_store.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "ShoppingCart")
public class ShoppingCart extends SuperEntity{

    private BigDecimal totalPrice;
    private Integer totalItem;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;


    @OneToMany(mappedBy = "shoppingCart")
    private List<CartItem> cartItems;

}
