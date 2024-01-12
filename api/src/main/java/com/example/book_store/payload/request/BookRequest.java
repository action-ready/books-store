package com.example.book_store.payload.request;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Builder
public class BookRequest {

    @NotBlank
    private String name;
    @Min(value = 1)
    private BigDecimal purchasePrice;
    @Min(value = 1)
    private BigDecimal sellingPrice;

    @Min(value = 10)
    private Integer pageCount;

    @Min(value = 1)
    private Integer quantity;

    @NotBlank
    private String publicationDate;

    @NotBlank
    private String description;

    @NotBlank
    private String status;


    private Long companyId;
    private Long publishersId;
    private Long coverTypesId;
    private Long translatorId;
    private Long authorId;
    private Long categoryId;
    private MultipartFile[] files;


}
