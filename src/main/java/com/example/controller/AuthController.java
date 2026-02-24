package com.example.controller;

import com.example.dto.APIResponse;
import com.example.dto.LogiDto;
import com.example.dto.UserDto;
import com.example.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/employee")
public class AuthController {
    private AuthService authService;
    private AuthenticationManager authenticationManager;

    public AuthController(AuthService authService, AuthenticationManager authenticationManager) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
    }
//    @PostMapping("/hello")
//    public String hello(){
//        return "Hello";
//    }
//    @PostMapping("/hi")
//    public String hi(){
//        return "Hii";
//    }

    @PostMapping("/patient_signup")
    public ResponseEntity<APIResponse<String>> patientSignupCheck(
            @RequestBody UserDto userDto
    ) {
        APIResponse<String> response = authService.register(userDto,"ROLE_PATIENT");
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }
    @PostMapping("/doctor_signup")
    public ResponseEntity<APIResponse<String>> doctorSignupCheck(
            @RequestBody UserDto userDto
    ) {
        APIResponse<String> response = authService.register(userDto,"ROLE_DOCTOR");
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    @PostMapping("/login")
    public ResponseEntity<APIResponse<String>> loginCheck(
            @RequestBody LogiDto logiDto
    ) {
        APIResponse<String> response = new APIResponse<>();
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(logiDto.getUsername(), logiDto.getPassword());
        try {
            authenticationManager.authenticate(token);

            response.setMessage("Login Successfully");
            response.setStatus(200);
            response.setData("Transaction Completed");
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (BadCredentialsException e) {

            System.out.println("login failed " + e.getMessage());
            System.out.println();
            response.setMessage("Check Your Credential");
            response.setStatus(404);
            response.setData("User Not Found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }


    }

}


