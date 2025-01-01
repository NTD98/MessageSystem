package com.example.demo.repository;

import com.example.demo.entity.MessageEntity;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import reactor.core.publisher.Flux;

public interface MessageRepository extends ReactiveCassandraRepository<MessageEntity, Long> {
    @AllowFiltering
    Flux<MessageEntity> findAllByAuthorId(String senderId);
}
