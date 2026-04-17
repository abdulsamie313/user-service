package com.company.platform.userservice.model;

public class HelloResponse {

    private String message;

    public HelloResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}