package com.example.book_store.controller;


import com.example.book_store.payload.request.BookRatingDTORequest;
import com.example.book_store.service.BookRatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class BookRatingController {

    private final BookRatingService bookRatingService;


    @PostMapping("/ratings")
    public ResponseEntity<?> createRating(@RequestBody BookRatingDTORequest request) {
        bookRatingService.create(request);
        return ResponseEntity.ok("Create success");
    }

    @GetMapping("/book/{id}/ratings")
    public ResponseEntity<?> getRatingByBookId(@PathVariable Long id) {
        return ResponseEntity.ok(bookRatingService.getRatingBook(id));
    }
}
