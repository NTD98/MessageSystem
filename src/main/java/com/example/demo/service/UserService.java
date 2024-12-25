package com.example.demo.service;

import com.example.demo.dto.User;
import reactor.core.publisher.Mono;

public interface UserService {
    Mono<String> createUser(User user);
}
