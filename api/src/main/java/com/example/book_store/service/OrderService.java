package com.example.book_store.service;

import com.example.book_store.payload.request.OrderFilterRequest;
import com.example.book_store.payload.request.OrderRequest;
import com.example.book_store.payload.response.OrderResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {


    void createOrder(OrderRequest request, Long accountId);

    void updateStatusOrder(Long orderId, String status);


    Page<OrderResponse> getAll(Pageable pageable, OrderFilterRequest request);
}
