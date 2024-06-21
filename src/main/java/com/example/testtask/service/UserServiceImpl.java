package com.example.testtask.service;

import com.example.testtask.exception.UserExistingEmailException;
import com.example.testtask.exception.UserNotFoundException;
import com.example.testtask.model.Users;
import com.example.testtask.repo.UserRepo;
import com.example.testtask.response.ResponseHandler;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    UserRepo userRepo;
    @Override
    public ResponseEntity<Object> save(Users user) {
        if (user.getEmail().equals(userRepo.findByEmail(user.getEmail()).get().getEmail()))
            throw new UserExistingEmailException("There is user with the same email");
        long id = userRepo.save(user).getId();
        return ResponseHandler.responseBuilder("user was saved", HttpStatus.OK, id);
    }

    @Override
    public ResponseEntity<Object> findById(long id) {
        if (!userRepo.findById(id).isPresent())
            throw new UserNotFoundException("User does not exist");
        return ResponseHandler.responseBuilder("serched user", HttpStatus.OK, userRepo.findById(id).get());
    }

    @Override
    public ResponseEntity<Object> updateUser(long id) {
        if (!userRepo.findById(id).isPresent())
            throw new UserNotFoundException("User does not exist");
        Users user = userRepo.findById(id).get();
        Map<String, String> updateResponse = new HashMap<>();
        updateResponse.put("id", Long.toString(user.getId()));
        updateResponse.put("previous status", user.getStatus());
        if (user.getStatus().equalsIgnoreCase("offline")) {
            user.setStatus("online");
            updateResponse.put("current status", user.getStatus());
        } else {
            user.setStatus("offline");
            updateResponse.put("current status", user.getStatus());
        }
        userRepo.save(user);
        return ResponseHandler.responseBuilder("user was updated", HttpStatus.OK, updateResponse);
    }
}
