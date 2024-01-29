package com.electronic.store.entities;

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
@Entity
@Table(name="cart")
public class Cart {
    @Id
    private String cartId;
    private Date createdAt;
    @OneToOne
    private User user;

    //mapping cartItem entity
    @OneToMany(mappedBy ="cart" ,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<CartItem> item=new ArrayList<>();
}
