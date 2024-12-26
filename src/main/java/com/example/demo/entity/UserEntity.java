package com.example.demo.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.Instant;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(value = "user",keyspace = "keyspace_user")
public class UserEntity {
    @PrimaryKey
    @Id
    @Builder.Default
    private UUID userid = UUID.randomUUID();
    @Column("create_time")
    private Instant createTime;
    @Column("last_login_time")
    private Instant lastLoginTime;
    private String email;
    @Column("display_name")
    private String displayName;
    private String phone;
    private String status;
    private String username;
}
