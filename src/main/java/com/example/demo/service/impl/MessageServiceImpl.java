package com.example.demo.service.impl;


import com.example.demo.dto.Message;
import com.example.demo.entity.MessageEntity;
import com.example.demo.repository.MessageRepository;
import com.example.demo.service.MessageService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.ExecutorService;

@Service
@Log4j2
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

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

    @Override
    @Async("virtualThreadPoolExecutor")
    public void sendBatchMessage(List<Message> message) {
        messageRepository.saveAll(message.parallelStream().map(item->MessageEntity.builder()
                        .content(item.getContent())
                        .sender(item.getSender())
                        .receiver(item.getReceiver())
                        .sent_at(item.getSent_at())
                        .build()).toList())
                .doOnComplete(()->
                        log.info("Batch send finished"))
                .doOnComplete(()->
                        log.info("Successfully sent batch message"))
                .doOnError(throwable ->
                        log.error(throwable.getMessage())).subscribe();
    }
}
