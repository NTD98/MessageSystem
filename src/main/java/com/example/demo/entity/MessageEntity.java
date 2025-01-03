package com.example.demo.entity;


import lombok.Builder;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@Table(value = "messages",keyspace = "keyspace_message")
@Builder
public class MessageEntity {
    @PrimaryKeyColumn(name = "channel_id", type = PrimaryKeyType.PARTITIONED)
    private long channelId;

    @PrimaryKeyColumn(name = "bucket", type = PrimaryKeyType.PARTITIONED)
    private LocalDate bucket;

    @PrimaryKeyColumn(name = "message_id", type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    private long messageId;
    private String content;
    @Column(value = "author_id")
    private String authorId;
}
