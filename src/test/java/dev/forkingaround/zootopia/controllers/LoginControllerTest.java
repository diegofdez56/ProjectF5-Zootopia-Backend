package dev.forkingaround.zootopia.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(LoginController.class)
class LoginControllerTest {

    private final MockMvc mockMvc;

    LoginControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void testLogin() throws Exception {
        mockMvc.perform(get("/api/v1/login"))
               .andExpect(status().isOk())
               .andExpect(content().string("Login success"));
    }

    @Test
    void testLogout() throws Exception {
        mockMvc.perform(post("/api/v1/logout"))
               .andExpect(status().isOk())
               .andExpect(content().string("Logout success"));
    }
}
