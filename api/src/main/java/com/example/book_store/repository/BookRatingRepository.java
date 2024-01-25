package com.example.book_store.repository;

import com.example.book_store.entity.BookRating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRatingRepository extends JpaRepository<BookRating, Long> {



    List<BookRating> findByBookId(Long bookId);
}
