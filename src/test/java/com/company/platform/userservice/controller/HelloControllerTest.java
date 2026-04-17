package com.company.platform.userservice.controller;

import com.company.platform.userservice.model.HelloResponse;
import com.company.platform.userservice.service.HelloService;
import com.company.platform.userservice.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HelloController.class)
class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private HelloService helloService;

    @MockitoBean
    private UserService userService;

    @Test
    void shouldReturnHelloMessage() throws Exception {
        // Arrange
        when(helloService.getHelloMessage("Abdul"))
                .thenReturn(new HelloResponse("Hello Abdul"));

        // Act + Assert
        mockMvc.perform(get("/hello").param("name", "Abdul"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Hello Abdul"));
    }
}