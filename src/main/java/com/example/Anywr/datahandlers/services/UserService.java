package com.example.Anywr.datahandlers.services;

import com.example.Anywr.datahandlers.models.User;

public interface UserService {
//    User save(User user);
    User loadByUsername(String username);
}