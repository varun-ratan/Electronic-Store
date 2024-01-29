package com.electronic.store.dtos;

import com.electronic.store.entities.CartItem;
import com.electronic.store.entities.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CartDto {
    private String cartId;
    private Date createdAt;
    private User user;
    private List<CartItem> items=new ArrayList<>();

}
