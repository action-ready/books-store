package com.example.book_store.payload.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;


@Getter
@Setter
public class CustomPage<T> {
    private int totalPages;
    private long totalItems;
    private int currentPage;
    private List<T> content;

    public CustomPage(Page<T> page) {
        this.totalPages = page.getTotalPages();
        this.totalItems = page.getTotalElements();
        this.currentPage = page.getNumber() + 1;
        this.content = page.getContent();
    }
}
