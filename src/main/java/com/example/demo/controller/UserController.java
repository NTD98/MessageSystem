package com.example.demo.controller;

import com.example.demo.dto.User;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping
    public Mono<String> postMessage(@RequestBody User user) {
        // Logic to handle the message
        return userService.createUser(user);
    }
}
