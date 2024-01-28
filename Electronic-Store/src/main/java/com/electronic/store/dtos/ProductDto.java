package com.electronic.store.dtos;

import jakarta.persistence.Column;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductDto {

    private String productId;
    private String title;
    private String description;
    private int price;
    private int discountedPrice;
    private  int quantity;
    private Date addedDate;
    private boolean live;
    private boolean stock;
}
