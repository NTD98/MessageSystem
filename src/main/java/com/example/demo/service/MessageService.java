package com.example.demo.service;

import com.example.demo.dto.Message;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface MessageService {
    Flux<Message> getMessages(String senderId);
    Mono<String> sendMessage(Message message);
    void sendBatchMessage(List<Message> message);
}
