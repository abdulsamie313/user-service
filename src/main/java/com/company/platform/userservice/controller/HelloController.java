package com.company.platform.userservice.controller;

import com.company.platform.userservice.model.*;
import com.company.platform.userservice.service.HelloService;
import com.company.platform.userservice.service.UserService;

import org.springframework.data.domain.Page;

import java.util.List;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    private final HelloService helloService;
    private final UserService userService;

    public HelloController(HelloService helloService, UserService userService) {
        this.helloService = helloService;
        this.userService = userService;
    }

    @GetMapping("/hello")
    public HelloResponse sayHello(@RequestParam String name) {
        return helloService.getHelloMessage(name);
    }

    @PostMapping("/hello")
    public HelloResponse sayHelloPost(@Valid @RequestBody HelloRequest request) {
        return helloService.getHelloMessage(request.getName());
    }

    @PostMapping("/users")
    public UserResponse createUser(@RequestBody @Valid UserRequest request) {
        return userService.saveUser(request);
    }

    @GetMapping("/users")
    public List<UserResponse> getUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/users/{id}")
        public UserResponse updateUser(
                @PathVariable Long id,
                @RequestBody @Valid UserRequest request) {
        return userService.updateUser(id, request);
        }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/users/paged")
    public Page<UserResponse> getUsersPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size){
        return userService.getUsersPaginated(page, size);
    }

    @GetMapping("/users/search")
    public Page<UserResponse> searchUsers(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size){
        return userService.searchUsersByName(name, page, size);
    }

    @PostMapping("/login")
    public String login(@RequestBody @Valid LoginRequest request) {
        return userService.loginUser(request);
    }

}