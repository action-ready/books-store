package com.example.book_store.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Book")
public class Book extends SuperEntity {

    private String name;
    private BigDecimal purchasePrice;
    private BigDecimal sellingPrice;
    private Integer pageCount;
    private Integer quantity;
    private Date publicationDate;
    private String description;
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "publishers_id")
    private Publishers publishers;

    @ManyToOne
    @JoinColumn(name = "coverTypes_id")
    private CoverTypes coverTypes;

    @ManyToOne
    @JoinColumn(name = "translator_id")
    private Translator translator;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "book")
    private List<BookImage> bookImage;


    enum Status {
        ACTIVE,
        INACTIVE
    }
}
