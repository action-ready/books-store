package com.example.book_store.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity

@Table(name = "Order")
public class Order extends SuperEntity {

    private String orderCode;
    @Enumerated(EnumType.STRING)
    private Status status;
    private BigDecimal totalPrice;



    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToMany(mappedBy = "order")
    public List<OrderDetails> orderDetails;

    public enum Status {
        PROCESSING,
        SHIPPED,
        CANCELED,
        FULFILLED,

    }
}
