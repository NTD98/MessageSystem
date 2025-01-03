package com.example.demo.listener;

import com.example.demo.dto.Message;
import com.example.demo.service.MessageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
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
            groupId = "message-consumer-group",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listen(List<String> message) {
        message.parallelStream().forEach(msg ->{
            try {
                List<Message> messageList = objectMapper.readValue(msg, new TypeReference<>() {
                });
                System.out.println("Received message: " + messageList.getFirst().getChannelId());
                messageService.sendBatchMessage(messageList);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
    }
}