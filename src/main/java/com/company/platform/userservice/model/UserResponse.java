package com.company.platform.userservice.model;

public class UserResponse {

    private Long id;

    private String name;

    public UserResponse(){

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }






}
