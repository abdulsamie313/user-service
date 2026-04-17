package com.company.platform.userservice.service;

import com.company.platform.userservice.entity.User;
import com.company.platform.userservice.exception.ResourceNotFoundException;
import com.company.platform.userservice.model.LoginRequest;
import com.company.platform.userservice.model.UserRequest;
import com.company.platform.userservice.model.UserResponse;
import com.company.platform.userservice.repository.UserRepository;
import com.company.platform.userservice.util.JwtUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse saveUser(UserRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        User savedUser = userRepository.save(user);

        return new UserResponse(savedUser.getId(), savedUser.getName());
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> new UserResponse(user.getId(), user.getName()))
                .toList();
    }

    public UserResponse updateUser(Long id, UserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        user.setName(request.getName());

        User updatedUser = userRepository.save(user);

        return new UserResponse(updatedUser.getId(), updatedUser.getName());
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        userRepository.delete(user);
    }

    public Page<UserResponse> getUsersPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        return userRepository.findAll(pageable)
                .map(user -> new UserResponse(user.getId(), user.getName()));
    }

    public Page<UserResponse> searchUsersByName(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        return userRepository
                .findByNameContainingIgnoreCase(name, pageable)
                .map(user -> new UserResponse(user.getId(), user.getName()));
    }

    public String loginUser(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid Credentials"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid Credentials");
        }

        return JwtUtil.generateToken(user.getEmail());
    }
}