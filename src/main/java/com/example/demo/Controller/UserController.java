package com.example.demo.Controller;

import com.example.demo.Status;
import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
  ***REMOVED***

    @PostMapping("/users/register")
    public Status registerUser(@Valid @RequestBody User newUser) {
        System.out.println(newUser);
        List<User> users = userRepository.findAll();

        if (newUser.getEmail() == null) return Status.FAILURE;

        for (User user: users) {
            if (user.equals(newUser) && user.getEmail().equals(newUser.getEmail())) {
                System.out.println("User Already exists!");
                return Status.USER_ALREADY_EXISTS;
          ***REMOVED***
      ***REMOVED***

        userRepository.save(newUser);
        return Status.SUCCESS;
  ***REMOVED***

    @PostMapping("/users/login")
    public Status loginUser(@Valid @RequestBody User user) {
        List<User> users = userRepository.findAll();
        System.out.println(user);

        for (User other : users) {
            System.out.println(other);
            if (other.equals(user)) {
                user.setLoggedIn(true);
                userRepository.save(user);
                return Status.SUCCESS;
          ***REMOVED***
      ***REMOVED***

        return Status.FAILURE;
  ***REMOVED***

    @PostMapping("/users/logout")
    public Status logUserOut(@Valid @RequestBody User user) {
        List<User> users = userRepository.findAll();

        for (User other : users) {
            if (other.equals(user)) {
                user.setLoggedIn(false);
                userRepository.save(user);
                return Status.SUCCESS;
          ***REMOVED***
      ***REMOVED***

        return Status.FAILURE;
  ***REMOVED***

    @DeleteMapping("/users/all")
    public Status deleteUsers() {
        userRepository.deleteAll();
        return Status.SUCCESS;
  ***REMOVED***
***REMOVED***
