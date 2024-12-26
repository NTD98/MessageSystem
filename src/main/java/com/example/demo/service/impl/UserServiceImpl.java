package com.example.demo.service.impl;

import com.example.demo.dto.User;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Instant;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public Mono<String> createUser(User user) {
        var now = Instant.now();
        return userRepository.save(UserEntity.builder()
                        .username(user.getUsername())
                        .email(user.getEmail())
                        .phone(user.getPhone())
                        .status("ACTIVE")
                        .displayName(user.getDisplayName())
                        .createTime(now)
                        .lastLoginTime(now)
                        .build()).map(UserEntity::toString);
    }
}
