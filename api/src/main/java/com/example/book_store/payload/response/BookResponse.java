package com.example.book_store.payload.response;


import com.example.book_store.entity.*;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private BigDecimal purchasePrice;
    private BigDecimal sellingPrice;
    private Integer pageCount;
    private Integer quantity;
    private Date publicationDate;
    private String description;
    private String companyName;
    private String publishersName;
    private String coverTypesName;
    private String translatorName;
    private String authorName;
    private String categoryName;
    private List<String> bookImageUrl;
}
