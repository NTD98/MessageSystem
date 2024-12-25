package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private String displayName;      // User's display name
    private String email;            // User's email address
    private String phone;            // User's phone number
    private String status;           // User's status (e.g., active/inactive)
    private String username;         // User's username
}
