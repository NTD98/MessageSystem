package com.example.demo.controller;

import com.example.demo.dto.Message;
import com.example.demo.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/v1/message")
@AllArgsConstructor
public class MessageController {
    private MessageService messageService;
    
    @GetMapping
    public Flux<Message> getAllMessagesBySender(@RequestParam String senderId) {
        // Logic to retrieve messages by sender
        return messageService.getMessages(senderId);
    }
}
