package dev.forkingaround.zootopia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/api/v1/login")
public class LoginController {
    public String login() {
        return "Login success";
    }

    @PostMapping("/api/v1/logout")
    public String logout() {
        return "Logout success";

    }
}
