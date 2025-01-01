package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.VirtualThreadTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import wiki.xsx.core.snowflake.config.Snowflake;

import java.util.concurrent.*;

@Configuration
@EnableAsync
public class AppConfig {
    @Value("${app.snowflake.worker-id}")
    private int workerId;
    @Value("${app.snowflake.datacenter}")
    private int dataCenterId;
    // Create the VirtualThreadExecutor bean
    @Bean
    public ExecutorService virtualThreadPoolExecutor() {
        return Executors.newVirtualThreadPerTaskExecutor();
    }
    @Bean
    public Snowflake snowflake() {
        return new Snowflake(workerId, dataCenterId);
    }
}
