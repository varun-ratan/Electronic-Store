package com.electronic.store.services.impl;

import com.electronic.store.dtos.AddItemToCartRequest;
import com.electronic.store.dtos.CartDto;
import com.electronic.store.entities.Cart;
import com.electronic.store.entities.CartItem;
import com.electronic.store.entities.Product;
import com.electronic.store.entities.User;
import com.electronic.store.repositories.CartItemRepository;
import com.electronic.store.repositories.CartRepository;
import com.electronic.store.repositories.ProductRepository;
import com.electronic.store.repositories.UserRepository;
import com.electronic.store.services.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CartDto addItemToCart(String userId, AddItemToCartRequest request) {
       int quantity= request.getProductQuantity();
       String productId=request.getProductId();

//       if(quantity<=0)
//       {
//           throw new RuntimeException("Requested quantity is not valid");
//       }
       //fetch the product
       Product product= productRepository.findById(productId).orElseThrow(()->new RuntimeException("The product id is not present in database"));
       User user=userRepository.findById(userId).orElseThrow(()->new RuntimeException("The user is not present with given id"));
       Cart cart=null;
       try
       {
           cart=cartRepository.findByUser(user).get();
       }catch (NoSuchElementException e)
       {
           cart =new Cart();
           cart.setCartId(UUID.randomUUID().toString());
           cart.setCreatedAt(new Date());
       }
       AtomicReference<Boolean> updated= new AtomicReference<>(false);
      List<CartItem> cartItemList=cart.getItem();
      List<CartItem> updatedItems= cartItemList.stream().map(item->
       {
           if(item.getProduct().getProductId().equals(productId))
           {
               item.setQuantity(quantity);
               item.setTotalPrice(quantity*product.getPrice());
               updated.set(true);
           }
           return  item;
       }).collect(Collectors.toList());
      cart.setItem(updatedItems);
      if(!updated.get()) {
          CartItem cartItem = CartItem.builder()
                  .quantity(quantity)
                  .totalPrice(quantity * product.getPrice())
                  .cart(cart)
                  .product(product)
                  .build();
          cart.getItem().add(cartItem);
      }
       cart.setUser(user);
       Cart updatedCart=cartRepository.save(cart);
       return modelMapper.map(updatedCart,CartDto.class);
    }

    @Override
    public void removeItemFromCart(String userId, int cartItem) {

         CartItem cartItem1 =cartItemRepository.findById(cartItem).orElseThrow(()->new RuntimeException("CartItem is not present"));
        cartItemRepository.delete(cartItem1);
    }

    @Override
    public void clearCart(String userId) {
        User user=userRepository.findById(userId).orElseThrow(()->new RuntimeException("The user is not present with given id"));
        Cart cart=cartRepository.findByUser(user).orElseThrow(()->new RuntimeException("Cart of given user not present"));
        cart.getItem().clear();
        cartRepository.save(cart);
    }

    @Override
    public  CartDto getCartByUser(String userId)
    {
        User user=userRepository.findById(userId).orElseThrow(()->new RuntimeException("The user is not present with given id"));
        Cart cart=cartRepository.findByUser(user).orElseThrow(()->new RuntimeException("Cart of given user not present"));
        return modelMapper.map(cart,CartDto.class);
    }
}
