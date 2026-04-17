package com.company.platform.userservice.service;

import com.company.platform.userservice.model.HelloResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelloServiceTest {

    @Test
    void shouldReturnUppercaseHelloMessage() {
        HelloService helloService = new HelloService();

        HelloResponse response = helloService.getHelloMessage("Abdul");

        assertEquals("Hello ABDUL", response.getMessage());
    }
}