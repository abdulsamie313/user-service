package com.company.platform.userservice.service;

import com.company.platform.userservice.entity.User;
import com.company.platform.userservice.model.UserRequest;
import com.company.platform.userservice.model.UserResponse;
import com.company.platform.userservice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @Test
    void shouldCreateUser() {
        // Arrange
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        PasswordEncoder passwordEncoder = Mockito.mock(PasswordEncoder.class);

        UserService userService = new UserService(userRepository, passwordEncoder);

        UserRequest request = new UserRequest();
        request.setName("Abdul");
        request.setEmail("abdul@test.com");
        request.setPassword("123456");

        when(passwordEncoder.encode("123456")).thenReturn("encoded-password");

        User savedUser = new User();
        savedUser.setName("Abdul");
        savedUser.setEmail("abdul@test.com");
        savedUser.setPassword("encoded-password");

        when(userRepository.save(Mockito.any(User.class)))
                .thenReturn(savedUser);

        // Act
        UserResponse response = userService.saveUser(request);

        // Assert
        assertEquals("Abdul", response.getName());
    }
}