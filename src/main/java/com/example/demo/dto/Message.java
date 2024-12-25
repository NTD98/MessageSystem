package com.example.demo.dto;

import java.sql.Timestamp;

import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    private String message_id;

    private String content;

    private String receiver;

    private String sender;

    private Timestamp sent_at;
}