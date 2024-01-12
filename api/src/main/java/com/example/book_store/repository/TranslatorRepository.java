package com.example.book_store.repository;

import com.example.book_store.entity.Translator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TranslatorRepository extends JpaRepository<Translator, Long> {
}
