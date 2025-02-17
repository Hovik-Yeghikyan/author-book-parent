package com.vector.authorbookcommon.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    private int id;
    private String title;
    private double price;
    private AuthorDto author;
    private Date createdAt;
    private double priceUSD;
    private double priceRUB;
    private double priceBTC;
}
