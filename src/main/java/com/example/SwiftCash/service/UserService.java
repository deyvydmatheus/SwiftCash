package com.example.SwiftCash.service;

import com.example.SwiftCash.model.User;
import com.example.SwiftCash.model.dto.UserDTO;
import com.example.SwiftCash.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService{

    private final IUserRepository iUserRepository;

    @Autowired
    public UserService(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }

    @Override
    public List<User> findAll() {
        return iUserRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return iUserRepository.findById(id);
    }

    @Override
    public Optional<User> signIn (UserDTO userDTO) {
        Optional<User> userOptional = iUserRepository.findByEmail(userDTO.getEmail());

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            if (userDTO.getPassword().equals(userOptional.get().getPassword())) {
                return Optional.of(user);
            } else {
                throw new IllegalArgumentException("Invalid password");
            }
        } else {
            throw new IllegalArgumentException("Invalid email");
        }
    }

    @Override
    public void signUp (UserDTO userDTO) {
        Optional<User> userOptional = iUserRepository.findByEmail(userDTO.getEmail());

        if (userOptional.isPresent()) {
            throw new IllegalArgumentException("Email already in use");
        }
        User user = new User();

        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());

        iUserRepository.save(user);
    }

    @Override
    public void updateUser(Long userId, UserDTO userDTO) {
        if (userId == null) {
            throw new IllegalArgumentException("User id cannot be null");
        }

        Optional<User> userOptional = iUserRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }

        User user = userOptional.get();

        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());

        iUserRepository.save(user);
    }

}
