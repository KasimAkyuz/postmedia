package com.akyuz.postmedia.controllers;

import com.akyuz.postmedia.entities.User;
import com.akyuz.postmedia.repos.UserRepository;
import com.akyuz.postmedia.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {

        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
    @PostMapping
    public User createUser(@RequestBody User newuser){
        return userService.saveOneUser(newuser);
    }
    @GetMapping("/{userId}")
    public User getOneUser(@PathVariable Long userId){
        return userService.getOneUser(userId);

    }
    @PutMapping("/{userId}")
    public User updateOneUser(@PathVariable Long userId
                             ,@RequestBody User updateUser ){
       return userService.updateOneUser(userId,updateUser);

    }
    @DeleteMapping("/{userId}")
    public void deleteOneUser(@PathVariable Long userId){
        userService.deleteById(userId);
    }


}
