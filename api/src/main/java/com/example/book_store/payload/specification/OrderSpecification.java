package com.example.book_store.payload.specification;


import com.example.book_store.entity.Account;
import com.example.book_store.entity.Order;
import com.example.book_store.payload.request.OrderFilterRequest;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class OrderSpecification {

    public static Specification<Order> buildWhere(OrderFilterRequest request) {
        Specification<Order> where = null;

        where = addSpecification(where, "status", request.getStatus());
        where = addSpecification(where, "phoneNumber", request.getPhoneNumber());
        return where;
    }


    private static Specification<Order> byField(String field, Object value) {
        return (root, query, criteriaBuilder) -> {
            if (field.equalsIgnoreCase("status")) {
                return criteriaBuilder.equal(root.get("status"), value.toString());
            }
            if (field.equalsIgnoreCase("phoneNumber")) {
                Join<Order, Account> fieldJoin = root.join("account");
                return criteriaBuilder.equal(fieldJoin.get("phoneNumber"), value.toString());
            }
            return null;
        };

    }

    private static Specification<Order> addSpecification(
            Specification<Order> where,
            String field, Object value) {

        if (value != null && !String.valueOf(value).isEmpty()) {
            return (where == null) ? byField(field, value) : where.and(byField(field, value));
        }
        return where;
    }
}
