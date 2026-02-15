package com.example.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/employee")
public class AuthController {
    @PostMapping("/hello")
    public String hello(){
        return "Hello";
    }
    @PostMapping("/hi")
    public String hi(){
        return "Hii";
    }
}
