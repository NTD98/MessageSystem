package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.Embedded;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessagePK implements Serializable {
    @PrimaryKey
    @Column("message_id")
    @Builder.Default
    private UUID messageId = UUID.randomUUID();
    private BigInteger bucket;
    private BigInteger channelId;
}
