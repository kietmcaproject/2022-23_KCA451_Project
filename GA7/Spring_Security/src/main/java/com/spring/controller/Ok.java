package com.spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Ok {

    @GetMapping("/home")
    public String home(){
        return "hello this is the first page";
    }
}
