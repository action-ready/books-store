package com.example.book_store.controller;


import com.example.book_store.payload.request.OrderFilterRequest;
import com.example.book_store.payload.request.OrderRequest;
import com.example.book_store.service.OrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


    @GetMapping("/orders")
    public ResponseEntity<?> getAllOrders(Pageable pageable, OrderFilterRequest request) {
        return ResponseEntity.ok(orderService.getAll(pageable, request));
    }

    @PostMapping("/account/{accountId}/orders")
    public ResponseEntity<?> createOrder(@RequestBody @Valid OrderRequest request, @PathVariable Long accountId) {
        orderService.createOrder(request, accountId);
        return ResponseEntity.ok("Create success");
    }

    @PutMapping("/orders/{orderId}")
    public ResponseEntity<?> updateStatusOrder(@PathVariable @NotNull Long orderId, @RequestParam @NotBlank String status) {
        orderService.updateStatusOrder(orderId, status);
        return ResponseEntity.ok("Update success");
    }
}
