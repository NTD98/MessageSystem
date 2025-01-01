package com.example.demo.service.impl;


import com.example.demo.config.AppConfig;
import com.example.demo.dto.Message;
import com.example.demo.entity.MessageEntity;
import com.example.demo.repository.MessageRepository;
import com.example.demo.service.MessageService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import wiki.xsx.core.snowflake.config.Snowflake;

import java.util.List;

@Service
@Log4j2
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final Snowflake snowflake;
    private final AppConfig appConfig;

    @Override
    public Flux<Message> getMessages(String senderId) {
        return messageRepository.findAllByAuthorName(senderId)
                .map(messageEntity -> Message.builder()
                        .ChannelId(messageEntity.getChannelId())
                        .sender(messageEntity.getAuthorName())
                        .content(messageEntity.getContent())
                        .sentAt(messageEntity.getSent_at())
                        .build());
    }

    @Override
    public Mono<String> sendMessage(Message message) {
        return messageRepository.save(MessageEntity.builder()
                        .content(message.getContent())
                        .authorName(message.getSender())
                        .sent_at(message.getSentAt())
                        .build())
                .map(messageEntity -> messageEntity.getMessageId().toString());
    }

    @Override
    @Async("virtualThreadPoolExecutor")
    public void sendBatchMessage(List<Message> message) {
        messageRepository.saveAll(message.parallelStream().map(item->MessageEntity.builder()
                        .bucket(appConfig.snowflake().getWorkerId())
                        .channelId(item.getChannelId())
                        .messageId(snowflake.nextId())
                        .content(item.getContent())
                        .authorName(item.getSender())
                        .sent_at(item.getSentAt())
                        .build()).toList())
                .doOnComplete(()->
                        log.info("Batch send finished"))
                .doOnComplete(()->
                        log.info("Successfully sent batch message"))
                .doOnError(throwable ->
                        log.error(throwable.getMessage())).subscribe();
    }
}
