package com.electronic.store.controllers;

import com.electronic.store.dtos.AddItemToCartRequest;
import com.electronic.store.dtos.ApiResponseMessage;
import com.electronic.store.dtos.CartDto;
import com.electronic.store.helper.Helper;
import com.electronic.store.services.CartService;
import com.sun.security.auth.PrincipalComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/{userId}")
    public ResponseEntity<CartDto> addItemToCart(@PathVariable String userId, @RequestBody AddItemToCartRequest addItemToCartRequest)
    {
     CartDto cartDto=cartService.addItemToCart(userId,addItemToCartRequest);
     return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponseMessage> removeItemFromCart(@PathVariable String userId)
    {
        cartService.clearCart(userId);
        ApiResponseMessage responseMessage=ApiResponseMessage.builder()
                .message("Not cart is blank!!")
                .success(true)
                .status(HttpStatus.OK)
                .build();
        return  new ResponseEntity<>(responseMessage,HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<CartDto> getCart(@PathVariable String userId)
    {
        CartDto cartDto=cartService.getCartByUser(userId);
        return new ResponseEntity<>(cartDto,HttpStatus.OK);
    }

}
