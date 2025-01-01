package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.VirtualThreadTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.*;

@Configuration
@EnableAsync
public class AppConfig {
    // Create the VirtualThreadExecutor bean
    @Bean
    public ExecutorService virtualThreadPoolExecutor() {
        return Executors.newVirtualThreadPerTaskExecutor();
    }
}
