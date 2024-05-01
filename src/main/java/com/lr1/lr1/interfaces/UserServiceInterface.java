package com.lr1.lr1.interfaces;

import java.util.List;
import java.util.Optional;

import com.lr1.lr1.model.User;
public interface UserServiceInterface {
    List<User> showAllUsers();
    User addUser(String email, String password);
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    void deleteById(Long id);
    User updateUser(User user);
    
}
