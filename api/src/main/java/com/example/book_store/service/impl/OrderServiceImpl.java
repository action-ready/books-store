package com.example.book_store.service.impl;


import com.example.book_store.entity.*;
import com.example.book_store.payload.request.OrderRequest;
import com.example.book_store.repository.*;
import com.example.book_store.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final BookRepository bookRepository;
    private final OrderDetailsRepository orderDetailsRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    @Transactional
    public void createOrder(OrderRequest request, Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow();


        Order order = new Order();
        order.setStatus(Order.Status.PROCESSING);
        order.setAccount(account);

        order.setOrderCode("HĐ" + UUID.randomUUID());
        order.setTotalPrice(request.getTotalPrice());

        if (account.getCustomers().isEmpty()) {
            account.setCustomers(new ArrayList<>());
            Customer customer = new Customer();
            customer.setFullName(request.getCustomer().getFullName());
            customer.setAddress(request.getCustomer().getAddress());
            customer.setPhoneNumber(request.getCustomer().getPhoneNumber());
            customer.setAccount(account);
            Customer savedCustomer = customerRepository.save(customer);
            account.getCustomers().add(savedCustomer);
        } else {
            Customer customer = account.getCustomers().get(0);
            customer.setPhoneNumber(request.getCustomer().getPhoneNumber());
            customer.setFullName(request.getCustomer().getFullName());
            customer.setAddress(request.getCustomer().getAddress());
            customer.setAccount(account);
            customerRepository.save(customer);
        }

        List<OrderDetails> orderDetailsList = new ArrayList<>();

        for (CartItem o : account.getShoppingCart().get(0).getCartItems()) {
            Book book = o.getBook();
            book.setQuantity(book.getQuantity() - o.getQuantity());
            bookRepository.save(book);
            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setOrder(order);
            orderDetails.setAccount(account);
            orderDetails.setTotalItem(o.getQuantity());
            orderDetails.setCustomer(account.getCustomers().get(0));
            orderDetails.setBook(book);
            orderDetails.setTotalPrice(BigDecimal.valueOf(o.getQuantity())
                    .multiply(book.getSellingPrice()));
            orderDetails.setPayment(OrderDetails.Payment.valueOf(request.getPayment().toString()));
            orderDetailsList.add(orderDetails);
        }

        orderRepository.save(order);
        orderDetailsRepository.saveAll(orderDetailsList);
        cartItemRepository.deleteAll();

    }

    @Override
    @Transactional
    public void updateStatusOrder(Long orderId, String status) {
        Order order = orderRepository.findById(orderId).orElseThrow();

        order.setStatus(Order.Status.valueOf(status));

        if (status.equalsIgnoreCase("CANCELED")) {
            for (OrderDetails o : order.getOrderDetails()) {
                Book book = bookRepository.findById(o.getBook().getId()).orElseThrow();
                book.setQuantity(book.getQuantity() + o.getTotalItem());
                bookRepository.save(book);
            }

            order.setTotalPrice(BigDecimal.ZERO);
        }

        orderRepository.save(order);
    }

}
