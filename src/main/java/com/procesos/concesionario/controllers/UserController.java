package com.procesos.concesionario.controllers;

import com.procesos.concesionario.models.User;
import com.procesos.concesionario.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserServiceImpl userServiceImpl;
    @GetMapping(value = "/user/{id}")
    public User getById(@PathVariable(name = "id") Long id){
        return userServiceImpl.getUserById(id);
    }
    @PostMapping(value = "/user")
    public User createUser(@RequestBody User user){
        return userServiceImpl.createUser(user);
    }
}
