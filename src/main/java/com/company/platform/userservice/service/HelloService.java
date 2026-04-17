package com.company.platform.userservice.service;

import com.company.platform.userservice.model.HelloResponse;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public HelloResponse getHelloMessage(String name) {
        String upper = name.toUpperCase();
        return new HelloResponse("Hello " + upper);
    }
}