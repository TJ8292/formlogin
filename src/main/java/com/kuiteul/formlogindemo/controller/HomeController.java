package com.kuiteul.formlogindemo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home() {
        return "home.html";
    }

    @GetMapping("/main")
    public String main() {
        return "main.html";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin.html";
    }
}
