package com.example.book_store.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.aspectj.weaver.ast.Or;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "OrderDetails")
public class OrderDetails extends SuperEntity {

    private BigDecimal totalPrice;
    private Integer totalItem;
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;


    public  enum Payment {
        CASH,
        CREDIT_CARD,
        TRANSFER,
    }
}
