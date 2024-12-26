package com.example.demo.entity;


import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@Table(value = "message",keyspace = "keyspace_message")
@Builder
public class MessageEntity {
    @PrimaryKey
    @Id
    @Column("message_id")
    @Builder.Default
    private UUID messageId = UUID.randomUUID();
    private String content;

    private String receiver;

    private String sender;

    private Timestamp sent_at;
}
