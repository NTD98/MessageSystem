package com.example.demo.entity;


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
@Table(value = "messages",keyspace = "keyspace_messages")
public class MessageEntity {
    @PrimaryKey
    @Id
    @Column("message_id")
    private UUID messageId;
    private String content;

    private String receiver;

    private String sender;

    private Timestamp sent_at;
}
