package com.example.book_store.service;

import com.example.book_store.payload.request.BookFilterRequest;
import com.example.book_store.payload.request.BookRequest;
import com.example.book_store.payload.response.BookResponse;
import com.example.book_store.payload.response.CustomPage;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
public interface BookService {


    BookResponse createBook(BookRequest request) throws IOException;

    BookResponse updateBook(Long bookId, BookRequest request)throws IOException;

    List<BookResponse> getAll();

    CustomPage<BookResponse> getAll(Pageable pageable, BookFilterRequest request);

    BookResponse getBookById(Long id);
}
