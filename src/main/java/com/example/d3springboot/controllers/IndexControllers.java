package com.example.d3springboot.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexControllers {
    @GetMapping
    public String index() {
        return "Hello index";
    }
}
