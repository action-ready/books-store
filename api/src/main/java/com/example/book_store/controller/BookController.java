package com.example.book_store.controller;


import com.example.book_store.payload.request.BookFilterRequest;
import com.example.book_store.payload.request.BookRequest;
import com.example.book_store.payload.response.BookResponse;
import com.example.book_store.payload.response.CustomPage;
import com.example.book_store.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookResponse> createBook(@Valid BookRequest request) throws IOException {
        return ResponseEntity.ok(bookService.createBook(request));
    }


    @GetMapping
    public ResponseEntity<List<BookResponse>> getAll() {
        return ResponseEntity.ok(bookService.getAll());
    }

    @GetMapping("/filters")
    public ResponseEntity<CustomPage<BookResponse>> getAll(Pageable pageable, BookFilterRequest request) {

        return ResponseEntity.ok(bookService.getAll(pageable, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getById(@PathVariable @Valid String id) {
        try {
            Long parsedId = Long.parseLong(id);
            return ResponseEntity.ok(bookService.getBookById(parsedId));
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid id format: " + id);
        }
    }
}
