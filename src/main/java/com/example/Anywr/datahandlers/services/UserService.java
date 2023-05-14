package com.example.Anywr.datahandlers.services;

import com.example.Anywr.datahandlers.models.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    void saveUser(User user);

    UserDetails loadUser(String username, String password);
}