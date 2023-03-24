package com.procesos.concesionario.controllers;

import com.procesos.concesionario.models.User;
import com.procesos.concesionario.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserServiceImpl userServiceImpl;
    @GetMapping(value = "/user/{id}")
    public ResponseEntity getById(@PathVariable(name = "id") Long id){
        Map response = new HashMap<>();
        try{
            response.put("message","Se encontró el usuario");
            response.put("data",userServiceImpl.getUserById(id));
            return new ResponseEntity(response, HttpStatus.OK);
        }catch(Exception e){
            response.put("message","No Se encontró el usuario");
            response.put("data", e.getMessage());
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }

    }
    @PostMapping(value = "/user")
    public ResponseEntity createUser(@RequestBody User user){
        Map response = new HashMap<>();
        try{
            response.put("message","Se guardó el usuario");
            response.put("data",userServiceImpl.createUser(user));
            return new ResponseEntity(response, HttpStatus.CREATED);
        }catch(Exception e){
            response.put("message","No Se guardó el usuario");
            response.put("data", e.getMessage());
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/users")
    public ResponseEntity getAll(){
        Map response = new HashMap<>();
        try{
            response.put("message","Se encontraron usuarios registrados");
            response.put("data",userServiceImpl.allUsers());
            return new ResponseEntity(response, HttpStatus.OK);
        }catch(Exception e){
            response.put("message","No Se encontraron usuarios");
            response.put("data", e.getMessage());
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }

    }
}
