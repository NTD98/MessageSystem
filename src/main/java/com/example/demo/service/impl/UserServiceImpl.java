package com.example.demo.service.impl;

import com.example.demo.dto.User;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.stream.IntStream;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public Mono<String> createUser(User user) {
        var list = new ArrayList<UserEntity>();
        IntStream.range(2, 1000).forEach(i -> {
            list.add(UserEntity.builder()
                    .displayName("User "+i)
                    .status("active")
                    .phone(user.getPhone())
                    .email("User "+i+"@gmail.com")
                    .username("User "+i)
                    .build());
        });
        userRepository.saveAll(list)
                .doOnComplete(() -> System.out.println("All users saved to the database."))
                .doOnError(error -> System.err.println("Error occurred: " + error.getMessage()))
                .subscribe(); // Triggers the save operation;
        return Mono.just("User created successfully");
    }
}
