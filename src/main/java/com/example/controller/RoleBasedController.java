package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class RoleBasedController {
    @GetMapping("/patient")
    public String patient(){
        return "hi Patient";
    }
    @GetMapping("/doctor")
    public String doctor(){
        return "hi Doctor";
    }

}
