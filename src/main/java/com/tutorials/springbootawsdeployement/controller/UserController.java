package com.tutorials.springbootawsdeployement.controller;

import com.tutorials.springbootawsdeployement.entity.Users;
import com.tutorials.springbootawsdeployement.exception.ResourceNotFoundException;
import com.tutorials.springbootawsdeployement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    //todo: Get all users
    @GetMapping
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }
    //todo: Get users by id
    @GetMapping("/{id}")
    public Users getUserById(@PathVariable("id") Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("user not found with id: " + id));
    }
    //todo: Create users
    @PostMapping
    public String createuser(@RequestBody Users users) {
        userRepository.save(users);
        return "user created successfully.";
    }
    //todo: Update users by id
    @PutMapping("/{id}")
    public Users updateUser(@RequestBody Users users, @PathVariable("id") Long id) {
        Users existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("user not found with id: " + id));
        existingUser.setFirstName(users.getFirstName());
        existingUser.setLastName(users.getLastName());
        existingUser.setEmail(users.getEmail());
        return userRepository.save(existingUser);
    }
    //todo: Delete users by id
    /*@DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userRepository.deleteById(id);
        return "user deleted successfully.";
    }*/
    @DeleteMapping("/{id}")
    public ResponseEntity<Users> deleteUserById(@PathVariable("id") Long id) {
        Users existingUsers = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("user not found with id: " + id));
        userRepository.delete(existingUsers);
        return ResponseEntity.ok().build();
    }
}
