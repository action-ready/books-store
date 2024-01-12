package com.example.book_store.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "BookRating")
public class BookRating extends SuperEntity {

    private Integer bookRate;

    @Column(columnDefinition = "text")
    private String description;


    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

}
