package com.example.Anywr.datahandlers.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
@Entity
public class User {
    @Id
    private Long id;
    private String username;
    private String password;

    public User (String username, String password){
        this.username = username;
        this.password = password;
    }
}
