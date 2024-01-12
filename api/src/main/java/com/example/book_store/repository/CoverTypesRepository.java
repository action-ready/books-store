package com.example.book_store.repository;

import com.example.book_store.entity.CoverTypes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoverTypesRepository extends JpaRepository<CoverTypes, Long> {
}
