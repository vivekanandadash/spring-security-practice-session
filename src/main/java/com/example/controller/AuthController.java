package com.example.controller;

import com.example.dto.APIResponse;
import com.example.dto.UserDto;
import com.example.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/employee")
public class AuthController {
    private AuthService  authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
//    @PostMapping("/hello")
//    public String hello(){
//        return "Hello";
//    }
//    @PostMapping("/hi")
//    public String hi(){
//        return "Hii";
//    }

    @PostMapping("/signup")
    public ResponseEntity<APIResponse<String>> signupCheck(
            @RequestBody UserDto userDto
            ){
        APIResponse<String> response = authService.register(userDto);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }
}
