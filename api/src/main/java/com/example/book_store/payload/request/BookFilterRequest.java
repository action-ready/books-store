package com.example.book_store.payload.request;


import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@Getter
@Setter
@Builder
public class BookFilterRequest {

    private String name;
    private List<Long> author;
    private List<Long> category;
    private List<Long> company;
    private List<Long> coverTypes;
    private List<Long> publishers;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private String sort;
}
