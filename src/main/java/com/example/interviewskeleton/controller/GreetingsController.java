package com.example.interviewskeleton.controller;

import com.example.interviewskeleton.service.GreetingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingsController {

    @Autowired
    private GreetingsService greetingService;

    @GetMapping("/greet/{name}")
    public ResponseEntity<String> greet(@PathVariable String name, @RequestParam(defaultValue = "en") String locale) {
        String greetingMessage = greetingService.getGreetingMessage(name, locale);

        System.out.println("test-brach-123");
        return ResponseEntity.ok(greetingMessage);
    }
}
