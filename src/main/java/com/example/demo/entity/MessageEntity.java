package com.example.demo.entity;


import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@Table(value = "message",keyspace = "keyspace_message")
@Builder
public class MessageEntity {
    @PrimaryKeyColumn(name = "channel_id", type = PrimaryKeyType.PARTITIONED)
    private Long channelId;

    @PrimaryKeyColumn(name = "bucket", type = PrimaryKeyType.PARTITIONED)
    private Long bucket;

    @PrimaryKeyColumn(name = "message_id", type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    private Long messageId;
    private String content;
    private String authorName;

    private Timestamp sent_at;
}
