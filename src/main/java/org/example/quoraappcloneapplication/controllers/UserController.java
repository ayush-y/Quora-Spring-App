package org.example.quoraappcloneapplication.controllers;

import org.example.quoraappcloneapplication.Services.UserFeedService;
import org.example.quoraappcloneapplication.Services.UserService;
import org.example.quoraappcloneapplication.dtos.UserDTO;
import org.example.quoraappcloneapplication.modles.Question;
import org.example.quoraappcloneapplication.modles.Users;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private UserService userService;

    private UserFeedService userFeedService;

    public UserController(UserService userService, UserFeedService userFeedService){
        this.userService = userService;
        this.userFeedService = userFeedService;
    }

    @GetMapping
    public List<Users> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/id")
    public ResponseEntity<Users> getUserById(@PathVariable Long id){
        Optional<Users> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping
    public Users createUser(@RequestBody UserDTO userDTO){

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

    @GetMapping("{id}/feed")
    public ResponseEntity<List<Question>> getUserFeed (@PathVariable Long userId, @RequestParam int page, @RequestParam int size){
        return ResponseEntity.ok(userFeedService.getUserFeed(userId, page, size));
    }

}
