package com.lr1.lr1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lr1.lr1.model.User;

public interface UserRepository extends JpaRepository<User, Long> { 
    Optional<User> findByEmail(String email);
}
