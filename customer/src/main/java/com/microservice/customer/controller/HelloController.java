package com.microservice.customer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    ResponseEntity<String> gethello()
    {
        return ResponseEntity.ok("hello");
    }
}
