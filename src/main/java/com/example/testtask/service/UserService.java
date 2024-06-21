package com.example.testtask.service;

import com.example.testtask.model.Users;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface UserService {
    ResponseEntity<Object> save(Users user);
    ResponseEntity<Object> findById(long id);
    ResponseEntity<Object> updateUser(long id);
}
