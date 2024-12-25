package com.example.demo.service;

import com.example.demo.dto.Message;
import reactor.core.publisher.Flux;

public interface MessageService {
    Flux<Message> getMessages(String senderId);
}
