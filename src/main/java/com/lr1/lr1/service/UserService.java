package com.lr1.lr1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lr1.lr1.interfaces.UserServiceInterface;
import com.lr1.lr1.model.User;
import com.lr1.lr1.model.WishList;
import com.lr1.lr1.repository.UserRepository;


@Service
public class UserService implements UserServiceInterface {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public List<User> showAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User addUser(String email, String password) {
        User user = new User(email,password);
        WishList wishList = new WishList(user);
        user.setWishList(wishList);
        return userRepository.save(user);
    }


    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }


    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }


    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }
    

    
}
