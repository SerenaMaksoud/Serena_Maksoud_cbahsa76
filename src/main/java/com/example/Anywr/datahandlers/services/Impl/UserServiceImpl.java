package com.example.Anywr.datahandlers.services.Impl;

import com.example.Anywr.datahandlers.mappers.UserMapper;
import com.example.Anywr.datahandlers.services.UserRepository;
import com.example.Anywr.datahandlers.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.Anywr.datahandlers.models.User;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

//    @Autowired
//    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Override
//    public User save(User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        return user.save(user);
//    }

    @Override
    public User loadByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }
}
