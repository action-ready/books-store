package com.example.book_store.service;

import com.example.book_store.payload.request.OrderRequest;

public interface OrderService {


    void createOrder(OrderRequest request, Long accountId);

    void updateStatusOrder(Long orderId, String status);

}
