package com.example.book_store.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "BookImage")
public class BookImage extends SuperEntity{

    @Column(columnDefinition = "text")
    private String url;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
}
