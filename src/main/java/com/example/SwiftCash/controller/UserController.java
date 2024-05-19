package com.example.SwiftCash.controller;

import com.example.SwiftCash.model.User;
import com.example.SwiftCash.model.dto.UserDTO;
import com.example.SwiftCash.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
@Validated
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/all-users")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @PostMapping(value = "/user-signup")
    public ResponseEntity<String> signUp(@Valid @RequestBody UserDTO userDto) {
       try {
           userService.signUp(userDto);
           return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");

       } catch (IllegalArgumentException e) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
       }
    }

    @PutMapping(value = "/{userId}/update-user")
    public ResponseEntity<?> updateUser(@PathVariable Long userId, @RequestBody UserDTO updatedUserDto) {
        try {
            userService.updateUser(userId, updatedUserDto);
            return ResponseEntity.status(HttpStatus.OK).body("User updated successfully");

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping(value = "/user-login")
    public ResponseEntity<?> login(@RequestBody UserDTO userLogIn) {
        try {
            userService.signIn(userLogIn);
            return ResponseEntity.status(HttpStatus.OK).body("User logged in successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
