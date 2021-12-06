package com.example.intellifridge.repositories;

import com.example.intellifridge.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Long > {
    User findByUsername(String username);
    User findByEmail(String email);
}
