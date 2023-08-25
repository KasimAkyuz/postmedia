package com.akyuz.postmedia.services;

import com.akyuz.postmedia.entities.User;
import com.akyuz.postmedia.repos.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveOneUser(User newuser) {
        return userRepository.save(newuser);
    }

    public User getOneUser(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User updateOneUser(Long userId, User updateUser) {
        Optional<User> user=userRepository.findById(userId);
        if(user.isPresent()){
            User foundUser = user.get();
            foundUser.setUserName(updateUser.getUserName());
            foundUser.setPassword(updateUser.getPassword());

            return userRepository.save(foundUser);
        }else return null;
    }

    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }


    public User getOneUserById(Long userId) {
       return userRepository.findById(userId).orElse(null);
    }

    public User getOneUserByUsername(String username) {
        return userRepository.findByUserName(username);
    }
}
