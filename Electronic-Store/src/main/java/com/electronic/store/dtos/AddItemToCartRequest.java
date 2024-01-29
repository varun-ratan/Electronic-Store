package com.electronic.store.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddItemToCartRequest {

    private String productId;

    private int productQuantity;
}
