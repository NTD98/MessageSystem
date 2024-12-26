package com.example.demo.controller;

import com.example.demo.dto.Message;
import com.example.demo.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/message")
@AllArgsConstructor
public class MessageController {
    private MessageService messageService;

    @PostMapping
    public Mono<String> postMessage(@RequestBody Message message) {
        // Logic to handle the message
        return Mono.just("Received message: " + message);
    }
    
    @GetMapping
    public Flux<Message> getAllMessagesBySender(@RequestParam String senderId) {
        // Logic to retrieve messages by sender
        return messageService.getMessages(senderId);
    }
}
