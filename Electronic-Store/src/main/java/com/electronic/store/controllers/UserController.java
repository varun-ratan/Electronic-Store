package com.electronic.store.controllers;

import com.electronic.store.dtos.UserDto;
import com.electronic.store.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //create
    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto)
    {
        UserDto user= userService.createUser(userDto);
        return  new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    //update
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("userId") String userId, @RequestBody UserDto userDto)
    {
        UserDto updatedUser=userService.updateUser(userDto,userId);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }


    //delete
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestBody String userId)
    {
        userService.deleteUser(userId);
        return new ResponseEntity<>("User deleted successfully",HttpStatus.OK);
    }

    //get all
    @GetMapping("/getAllUser")
    public ResponseEntity<List<UserDto>> getAllUser()
    {
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }

    //get single

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable("userId") String userId)
    {
        UserDto user=userService.getUserById(userId);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    //get email
    @GetMapping("/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable("email") String email)
    {
        return new ResponseEntity<>(userService.getUserByEmail(email), HttpStatus.OK);
    }

    //search
    @GetMapping("/search/{keywords}")
    public ResponseEntity<List<UserDto>> searchUser(@PathVariable String keywords)
    {
        return  new ResponseEntity<>(userService.searchUser(keywords), HttpStatus.OK);
    }
}
