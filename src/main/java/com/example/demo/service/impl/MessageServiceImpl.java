package com.example.demo.service.impl;


import com.example.demo.dto.Message;
import com.example.demo.repository.MessageRepository;
import com.example.demo.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {

    private MessageRepository messageRepository;

    @Override
    public Flux<Message> getMessages(String senderId) {
        return messageRepository.findAllBySender(senderId)
                .map(messageEntity -> Message.builder()
                        .message_id(messageEntity.getMessageId().toString())
                        .sender(messageEntity.getSender())
                        .content(messageEntity.getContent())
                        .sent_at(messageEntity.getSent_at())
                        .build());
    }
}
