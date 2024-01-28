package com.electronic.store.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import jakarta.persistence.Id;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="products")
public class Product {

    @Id
    private String productId;
    private String title;
    @Column(length=10000)
    private String description;
    private int price;
    private int discountedPrice;
    private  int quantity;
    private Date addedDate;
    private boolean live;
    private boolean stock;

}
