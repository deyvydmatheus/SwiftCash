package com.example.SwiftCash.service;

import com.example.SwiftCash.model.User;
import com.example.SwiftCash.model.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IUserService {

    List<User> findAll();

    Optional<User> findById(Long id);

    Optional<User> signIn (UserDTO userDTO);

    void signUp(UserDTO userDTO);

    void updateUser(Long userId, UserDTO userDTO);
}
