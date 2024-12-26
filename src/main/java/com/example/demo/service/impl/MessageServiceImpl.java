package com.example.demo.service.impl;


import com.example.demo.dto.Message;
import com.example.demo.entity.MessageEntity;
import com.example.demo.repository.MessageRepository;
import com.example.demo.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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

    @Override
    public Mono<String> sendMessage(Message message) {
        return messageRepository.save(MessageEntity.builder()
                        .content(message.getContent())
                        .sender(message.getSender())
                        .receiver(message.getReceiver())
                        .sent_at(message.getSent_at())
                        .build())
                .map(messageEntity -> messageEntity.getMessageId().toString());
    }
}
