package com.example.book_store.payload.specification;


import com.example.book_store.entity.Book;
import com.example.book_store.payload.request.BookFilterRequest;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
public class BookSpecification {

    public static Specification<Book> buildWhere(BookFilterRequest request) {
        Specification<Book> where = null;

        where = addSpecification(where, "name", request.getName());
        where = addSpecification(where, "author", request.getAuthor());
        where = addSpecification(where, "category", request.getCategory());
        where = addSpecification(where, "company", request.getCompany());
        where = addSpecification(where, "coverTypes", request.getCoverTypes());
        where = addSpecification(where, "publishers", request.getPublishers());
        where = addPriceRangeSpecification(where, request.getMinPrice(), request.getMaxPrice());
        where = addSpecification(where, "sort", request.getSort());


        return where;
    }

    private static Specification<Book> addSpecification(
            Specification<Book> where, String field, Object value) {
        if (value != null && !String.valueOf(value).isEmpty()) {
            return (where == null) ? byField(field, value) : where.and(byField(field, value));
        }
        return where;
    }

    private static Specification<Book> byField(String field, Object value) {
        return (root, query, criteriaBuilder) -> {
            if ("name".equalsIgnoreCase(field)) {
                return criteriaBuilder.like(root.get(field), "%" + value + "%");
            }

            if (Arrays.asList("author", "category", "company", "coverTypes", "publishers").contains(field)) {
                Join<Book, Object> fieldJoin = root.join(field);
                if (value instanceof List) {
                    List<Long> fieldIds = (List<Long>) value;
                    return fieldJoin.get("id").in(fieldIds);
                }
            }

            if ("sort".equalsIgnoreCase(field)) {
                query.orderBy(criteriaBuilder.desc(root.get(value.toString())));
            }

            return null;
        };
    }

    private static Specification<Book> addPriceRangeSpecification(
            Specification<Book> where, BigDecimal minPrice, BigDecimal maxPrice) {
        return (root, query, criteriaBuilder) -> {
            Predicate pricePredicate = criteriaBuilder.conjunction();
            if (minPrice != null) {
                pricePredicate = criteriaBuilder.and(
                        pricePredicate, criteriaBuilder.greaterThanOrEqualTo(root.get("sellingPrice"), minPrice)
                );
            }
            if (maxPrice != null) {
                pricePredicate = criteriaBuilder.and(
                        pricePredicate, criteriaBuilder.lessThanOrEqualTo(root.get("sellingPrice"), maxPrice)
                );
            }
            return where == null ? pricePredicate : criteriaBuilder.and(where.toPredicate(root, query, criteriaBuilder), pricePredicate);
        };
    }
}
