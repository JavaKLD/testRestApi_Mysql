package com.example.testtask.repo;

import com.example.testtask.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<Users, Long> {
    Optional<Users> findById(Long aLong);
    Optional<Users> findByEmail(String email);
}
