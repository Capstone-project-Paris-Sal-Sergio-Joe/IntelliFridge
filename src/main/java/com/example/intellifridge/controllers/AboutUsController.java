package com.example.intellifridge.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutUsController {

    @GetMapping("/about-us")
    public String aboutUs(){
        return "about-us/about-us";
    }

}
