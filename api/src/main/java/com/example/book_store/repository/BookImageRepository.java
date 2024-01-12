package com.example.book_store.repository;

import com.example.book_store.entity.Book;
import com.example.book_store.entity.BookImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource()
public interface BookImageRepository extends JpaRepository<BookImage, Long> {

    void deleteAllByBook(Book book);
}
