package org.example.quoraappcloneapplication.Services;

import org.example.quoraappcloneapplication.dtos.UserDTO;
import org.example.quoraappcloneapplication.modles.Tag;
import org.example.quoraappcloneapplication.modles.User;
import org.example.quoraappcloneapplication.repositories.TagRepository;
import org.example.quoraappcloneapplication.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    private TagRepository tagRepository;

    public UserService(UserRepository userRepository, TagRepository tagRepository){

        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
    }
    public List<User> getAllUsers(){

        return userRepository.findAll();
    }
    public Optional<User> getUserById(Long id){

        return userRepository.findById(id);
    }
    public User createUser(UserDTO userDto){

        User user = new User();
        user.setUsername(userDto.getUserName());
        user.setPassword(userDto.getPassword());
        return userRepository.save(user);

    }
    public void deleteUser(Long id){

        userRepository.deleteById(id);
    }
    public void followTag(Long userId, Long tagId){
        User user = userRepository.findById(userId).orElseThrow(() ->
                new RuntimeException("User is not found"));
        Tag tag = tagRepository.findById(tagId).orElseThrow(() ->
                new RuntimeException("tag not found"));

        user.getFollowedTags().add(tag);
        userRepository.save(user);
    }
}
