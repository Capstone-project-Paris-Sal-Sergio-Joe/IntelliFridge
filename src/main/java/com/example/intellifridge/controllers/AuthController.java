package com.example.intellifridge.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String loginPage() {
        System.out.println("yo yo got got");
        return "login";
    }

    @PostMapping("/login")
    public String loginInfo() {
        System.out.println("yo you posted");
        return "login";
    }
}
