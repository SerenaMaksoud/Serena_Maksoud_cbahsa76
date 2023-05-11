package com.example.Anywr.datahandlers.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Collection;

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

}
