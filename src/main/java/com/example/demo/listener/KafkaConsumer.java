package com.example.demo.listener;

import com.example.demo.dto.Message;
import com.example.demo.service.MessageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class KafkaConsumer {
    private final ObjectMapper objectMapper;
    private final MessageService messageService;

    // This method will be triggered when a message is received on the 'my-topic' topic
    @KafkaListener(topics = "message-topic",
            groupId = "my-consumer-group",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listen(List<Message> message) {
        System.out.println("Received message: " + message.getFirst().getMessage_id());
        messageService.sendBatchMessage(message);
    }
}