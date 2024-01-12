package com.example.book_store.repository;

import com.example.book_store.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "companies", collectionResourceRel = "companies")
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
