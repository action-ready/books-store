package com.example.book_store.service.impl;


import com.example.book_store.entity.BookRating;
import com.example.book_store.payload.request.BookRatingDTORequest;
import com.example.book_store.payload.response.BookRatingDTOResponse;
import com.example.book_store.repository.BookRatingRepository;
import com.example.book_store.service.BookRatingService;
import lombok.RequiredArgsConstructor;
import org.aspectj.util.FuzzyBoolean;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookRatingServiceImpl implements BookRatingService {

    private final BookRatingRepository bookRatingRepository;
    private final ModelMapper modelMapper;

    @Override
    public void create(BookRatingDTORequest request) {
        BookRating bookRating = modelMapper.map(request, BookRating.class);

        bookRatingRepository.save(bookRating);
    }

    @Override
    public List<BookRatingDTOResponse> getRatingBook(Long bookId) {
        List<BookRating> bookRatings = bookRatingRepository.findByBookId(bookId);

        List<BookRatingDTOResponse> responses = bookRatings.stream()
                .map((rating) -> modelMapper.map(rating, BookRatingDTOResponse.class))
                .collect(Collectors.toList());

        return responses;
    }
}
