package com.example.book_store.repository;

import com.example.book_store.entity.Publishers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublishersRepository extends JpaRepository<Publishers, Long> {
}
