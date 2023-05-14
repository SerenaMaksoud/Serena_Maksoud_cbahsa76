package com.example.Anywr.datahandlers.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AuthenticationRequest {
    private String username;
    private String password;
}
