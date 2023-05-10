package com.example.Anywr.datahandlers.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class User {
    private Long id;
    private String username;
    private String password;
    private String token;
    private String first_name;
    private String last_name;
    private String role;
}
