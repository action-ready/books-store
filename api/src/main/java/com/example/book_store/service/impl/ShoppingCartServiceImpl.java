package com.example.book_store.service.impl;


import com.example.book_store.entity.Account;
import com.example.book_store.entity.Book;
import com.example.book_store.entity.CartItem;
import com.example.book_store.entity.ShoppingCart;
import com.example.book_store.payload.request.ShoppingCartRequest;
import com.example.book_store.payload.response.CartItemsDTOResponse;
import com.example.book_store.repository.AccountRepository;
import com.example.book_store.repository.BookRepository;
import com.example.book_store.repository.CartItemRepository;
import com.example.book_store.repository.ShoppingCartRepository;
import com.example.book_store.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final ModelMapper modelMapper;
    private final BookRepository bookRepository;
    private final AccountRepository accountRepository;
    private final CartItemRepository cartItemRepository;

//    @Override
//    @Transactional
//    public void createShoppingCart(ShoppingCartRequest request, Long accountId) {
//        Account account = accountRepository.findById(accountId).orElse(null);
//        if (account == null) {
//            throw new IllegalArgumentException("Invalid account ID: " + accountId);
//        }
//
//        ShoppingCart cart = account.getShoppingCart().isEmpty() ? new ShoppingCart() : account.getShoppingCart().get(0);
//        cart.setCartItems(new ArrayList<>());
//        cart.setAccount(account);
//        BigDecimal totalPrice = BigDecimal.ZERO;
//        int totalItems = 0;
//        if (request.getCartItems() != null) {
//            for (ShoppingCartRequest.CartItem item : request.getCartItems()) {
//                CartItem existingCartItem = cart.getCartItems().stream()
//                        .filter(ci -> ci.getBook().getId().equals(item.getBookId()))
//                        .findFirst()
//                        .orElse(null);
//
//                if (existingCartItem != null) {
//                    // If the cart item already exists, update the quantity
//                    existingCartItem.setQuantity(existingCartItem.getQuantity() + item.getQuantity());
//                } else {
//                    // Create a new cart item if it doesn't exist
//                    CartItem cartItem = new CartItem();
//                    Book book = bookRepository.findById(item.getBookId()).orElse(null);
//                    if (book != null) {
//                        cartItem.setBook(book);
//                        cartItem.setQuantity(item.getQuantity());
//                        cart.getCartItems().add(cartItem);
//                    }
//                }
//            }
//        }
//
//        for (CartItem cartItem : cart.getCartItems()) {
//            BigDecimal quantity = new BigDecimal(cartItem.getQuantity());
//            BigDecimal itemTotalPrice = cartItem.getBook().getSellingPrice().multiply(quantity);
//            totalPrice = totalPrice.add(itemTotalPrice);
//            totalItems += cartItem.getQuantity();
//        }
//
//        cart.setTotalPrice(totalPrice);
//        cart.setTotalItem(totalItems);
//
//        ShoppingCart savedCart = shoppingCartRepository.save(cart);
//        System.out.println(savedCart);
//    }


    @Override
    @Transactional
    public void createShoppingCart(ShoppingCartRequest request, Long accountId) {
        Account account = accountRepository.findById(accountId).orElse(null);
        if (account == null) {
            throw new IllegalArgumentException("Invalid account ID: " + accountId);
        }

        ShoppingCart cart = null;
        if (account.getShoppingCart().isEmpty()) {
            cart = new ShoppingCart();
            cart.setAccount(account);
        } else {
            cart = account.getShoppingCart().get(0);
        }

        List<CartItem> cartItems = cart.getCartItems();

        if (cartItems == null) {
            cartItems = new ArrayList<>();
            cart.setCartItems(cartItems);
        }

        for (ShoppingCartRequest.CartItem item : request.getCartItems()) {
            Book book = bookRepository.findById(item.getBookId()).orElse(null);
            if (book != null) {
                CartItem existingCartItem = findCartItemByBookId(cartItems, item.getBookId());
                if (existingCartItem != null) {
                    existingCartItem.setQuantity(existingCartItem.getQuantity() + item.getQuantity());
                    cartItemRepository.save(existingCartItem);
                } else {
                    CartItem cartItem = new CartItem();
                    cartItem.setBook(book);
                    cartItem.setQuantity(item.getQuantity());
                    cartItem.setShoppingCart(cart);
                    cartItems.add(cartItem);
                    cartItemRepository.save(cartItem);
                }
            }
        }

        // Merge cart items with the same book_id


        int totalQuantity = calculateTotalQuantity(cartItems);
        cart.setTotalItem(totalQuantity);

        // Calculate the total price based on the updated quantities
        BigDecimal totalPrice = calculateTotalPrice(cartItems);
        cart.setTotalPrice(totalPrice);

        // Save or update the shopping cart
        ShoppingCart savedCart = shoppingCartRepository.save(cart);

    }

    @Override
    public void increaseCartItemQuantity(Long cartItemId, int quantityToAdd) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cart item not found"));

        increaseCartItemQuantity(cartItem, quantityToAdd);
    }


    @Override
    public void decreaseCartItemQuantity(Long cartItemId, int quantityToSubtract) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cart item not found"));
        decreaseCartItemQuantity(cartItem, quantityToSubtract);
    }

    @Override
    public void deleteCart(Long cartId) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(cartId).orElseThrow(null);
        shoppingCart.setTotalPrice(BigDecimal.ZERO);
        shoppingCart.setTotalItem(0);
        shoppingCartRepository.save(shoppingCart);
        cartItemRepository.deleteAll(shoppingCart.getCartItems());
    }

    @Override
    public List<CartItemsDTOResponse> getCartByAccountId(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Account not found"));

        List<CartItem> cartItem = account.getShoppingCart().get(0).getCartItems();

        List<CartItemsDTOResponse> responses = cartItem.stream().map((cart) -> modelMapper.map(cart, CartItemsDTOResponse.class))
                .collect(Collectors.toList());
        responses.get(0).setTotalPriceCart(account.getShoppingCart().get(0).getTotalPrice());

        return responses;
    }


    public void increaseCartItemQuantity(CartItem cartItem, int quantityToAdd) {
        if (cartItem != null && quantityToAdd > 0) {
            int newQuantity = cartItem.getQuantity() + quantityToAdd;
            cartItem.setQuantity(newQuantity);
            cartItemRepository.save(cartItem);
        }
    }

    public void decreaseCartItemQuantity(CartItem cartItem, int quantityToSubtract) {
        if (cartItem != null && quantityToSubtract > 0) {
            int newQuantity = cartItem.getQuantity() - quantityToSubtract;
            if (newQuantity < 0) {
                // Optionally handle scenario where quantity becomes negative
                // You can choose to remove the item or set quantity to 0
                // Example: cartItemRepository.delete(cartItem);
                cartItemRepository.delete(cartItem);
            } else {
                cartItem.setQuantity(newQuantity);
            }
            cartItemRepository.save(cartItem);
        }
    }


    private CartItem findCartItemByBookId(List<CartItem> cartItems, Long bookId) {
        for (CartItem cartItem : cartItems) {
            if (cartItem.getBook().getId().compareTo(bookId) == 0) {
                return cartItem;
            }
        }
        return null;
    }

    private int calculateTotalQuantity(List<CartItem> cartItems) {
        int totalQuantity = 0;
        for (CartItem cartItem : cartItems) {
            totalQuantity += cartItem.getQuantity();
        }
        return totalQuantity;
    }

    private BigDecimal calculateTotalPrice(List<CartItem> cartItems) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (CartItem cartItem : cartItems) {
            BigDecimal itemPrice = cartItem.getBook().getSellingPrice();
            BigDecimal itemQuantity = new BigDecimal(cartItem.getQuantity());
            BigDecimal itemTotalPrice = itemPrice.multiply(itemQuantity);
            totalPrice = totalPrice.add(itemTotalPrice);
        }
        return totalPrice;
    }


}
