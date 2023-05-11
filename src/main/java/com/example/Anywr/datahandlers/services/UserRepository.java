package com.example.Anywr.datahandlers.services;

import com.example.Anywr.datahandlers.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
//    User findByUsername(String username);
}