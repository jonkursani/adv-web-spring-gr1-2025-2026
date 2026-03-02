package dev.jonkursani.restapigr1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@Controller // kontroller qe kthen html (faqe thymeleaf)
@RestController // kontroller qe kthen JSON
public class DemoController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello from my API";
    }
}