package com.example.demo.service;

import com.example.demo.dto.Message;
import reactor.core.publisher.Flux;
import java.util.List;

public interface MessageService {
    Flux<Message> getMessages(String senderId);
    void sendBatchMessage(List<Message> message);
}
