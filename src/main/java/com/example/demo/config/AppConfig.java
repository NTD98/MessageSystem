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
    public ExecutorService threadPoolExecutor() {
        int corePoolSize = 10;         // Minimum number of threads in the pool
        int maximumPoolSize = 50;      // Maximum number of threads in the pool
        long keepAliveTime = 60L;      // Time to keep idle threads alive
        TimeUnit unit = TimeUnit.SECONDS;
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(100);  // Task queue for waiting tasks

        // Create a ThreadPoolExecutor with the above parameters
        return new ThreadPoolExecutor(
                corePoolSize, maximumPoolSize, keepAliveTime, unit,
                workQueue,
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()  // Rejection policy
        );
    }
}
