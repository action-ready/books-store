package com.example.book_store.service;

import com.example.book_store.payload.request.BookRatingDTORequest;
import com.example.book_store.payload.response.BookRatingDTOResponse;

import java.util.List;

public interface BookRatingService {

    void create(BookRatingDTORequest request);

    List<BookRatingDTOResponse> getRatingBook(Long bookId);


}
