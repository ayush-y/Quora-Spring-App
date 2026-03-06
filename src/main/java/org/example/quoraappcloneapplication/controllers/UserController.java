package org.example.quoraappcloneapplication.controllers;

import org.example.quoraappcloneapplication.Services.UserService;
import org.example.quoraappcloneapplication.dtos.UserDTO;
import org.example.quoraappcloneapplication.modles.User;
import org.example.quoraappcloneapplication.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/id")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping
    public User createUser(@RequestBody UserDTO userDTO){

        return userService.createUser(userDTO);
    }

    @DeleteMapping("/id")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/{userId}/following/{tagId}")
    public ResponseEntity<Void> followTag(@PathVariable Long userId, @PathVariable Long tagId){
        userService.followTag(userId, tagId);

        return ResponseEntity.noContent().build();
    }

}
