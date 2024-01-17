package com.example.book_store.service.impl;


import com.example.book_store.entity.Book;
import com.example.book_store.entity.BookImage;
import com.example.book_store.payload.request.BookFilterRequest;
import com.example.book_store.payload.request.BookRequest;
import com.example.book_store.payload.response.BookResponse;
import com.example.book_store.payload.response.CustomPage;
import com.example.book_store.payload.response.WebResponse;
import com.example.book_store.payload.specification.BookSpecification;
import com.example.book_store.repository.BookImageRepository;
import com.example.book_store.repository.BookRepository;
import com.example.book_store.service.BookService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;
    private final BookImageRepository bookImageRepository;
    private final RedisTemplate<String, Object> redisTemplate;


    private void saveBookImages(Book book, List<MultipartFile> images) throws IOException {
        List<BookImage> imageList = new ArrayList<>();
        for (MultipartFile image : images) {
            BookImage bookImage = new BookImage();
            bookImage.setBook(book);
            String imageBase64 = Base64.getEncoder().encodeToString(image.getBytes());
            bookImage.setUrl(imageBase64);
            imageList.add(bookImage);
        }
        bookImageRepository.saveAll(imageList);
    }

    @Override
    public BookResponse createBook(BookRequest request) throws IOException {
        Book book = modelMapper.map(request, Book.class);
        Book savedBook = bookRepository.save(book);

        if (request.getFiles() != null) {
            saveBookImages(savedBook, Arrays.asList(request.getFiles()));
        }
        redisTemplate.delete("allBooks");

        return modelMapper.map(savedBook, BookResponse.class);
    }

    @Override
    public BookResponse updateBook(Long bookId, BookRequest request) throws IOException {
        Book existingBook = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Book not found"));

        modelMapper.map(request, existingBook);

        existingBook.getBookImage().clear();

        if (request.getFiles() != null) {
            saveBookImages(existingBook, Arrays.asList(request.getFiles()));
        }

        Book updatedBook = bookRepository.save(existingBook);

        redisTemplate.delete("allBooks");

        return modelMapper.map(updatedBook, BookResponse.class);
    }

    @Override
    public List<BookResponse> getAll() {
        Object object = redisTemplate.opsForValue().get("allBooks");
        if (object != null) {
            return (List<BookResponse>) object;
        }
        List<Book> books = bookRepository.findAll();
        List<BookResponse> responses = books.stream().map((book) -> modelMapper.map(book, BookResponse.class)).collect(Collectors.toList());
        redisTemplate.opsForValue().set("allBooks", responses);
        return responses;
    }

    @Override
    public CustomPage<BookResponse> getAll(Pageable pageable, BookFilterRequest request) {
        int pageSize = pageable.getPageSize() != 0 ? pageable.getPageSize() : 18;
        Specification<Book> where = BookSpecification.buildWhere(request);
        Pageable pageableCustom = PageRequest.of(pageable.getPageNumber(), pageSize);

        Page<Book> books =  bookRepository.findAll(where, pageableCustom);

        Page<BookResponse> responses = books.map(book -> modelMapper.map(book, BookResponse.class));
        return new CustomPage<>(responses);
    }

    @Override
    public BookResponse getBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow();
        BookResponse response = modelMapper.map(book, BookResponse.class);
        return response;
    }

}
