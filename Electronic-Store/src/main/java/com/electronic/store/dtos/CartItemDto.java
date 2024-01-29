package com.electronic.store.dtos;

import com.electronic.store.entities.Cart;
import com.electronic.store.entities.Product;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CartItemDto {
    private int cartItemId;
    private Product product;
    private int quantity;
    private int totalPrice;

}
