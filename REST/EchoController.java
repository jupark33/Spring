package com.example.echo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/v1")
public class EchoController {

    @GetMapping("hello")
    public String echo() {
        return "ok";
    }
    
}
