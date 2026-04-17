package com.company.platform.userservice.model;

import jakarta.validation.constraints.NotNull;

public class HelloRequest {

    @NotNull(message = "name cannot be null")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




}
