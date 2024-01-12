package com.example.book_store.repository;

import com.example.book_store.entity.BookRating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRatingRepository extends JpaRepository<BookRating, Long> {
}
