package dev.forkingaround.zootopia.controllers;


import dev.forkingaround.zootopia.dtos.UserDto;
import dev.forkingaround.zootopia.services.RegisterService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "${api-endpoint}/register")
public class RegisterController {

    RegisterService service;

    public RegisterController(RegisterService service) {
        this.service = service;
    }

    @PostMapping
    public String register(@RequestBody UserDto newUser) {
        System.out.println(newUser.getUsername());
        return service.save(newUser);
    }

} 
    

