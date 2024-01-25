package com.example.book_store.payload.response;

import com.example.book_store.entity.Account;
import com.example.book_store.entity.Book;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookRatingDTOResponse {
    private Long id;
    private Integer bookRate;
    private String description;
    private String accountFullName;
}
