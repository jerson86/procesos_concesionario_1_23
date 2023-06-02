package com.procesos.concesionario.controllers;

import com.procesos.concesionario.models.User;
import com.procesos.concesionario.services.UserService;
import com.procesos.concesionario.services.UserServiceImpl;
import com.procesos.concesionario.utils.ApiResponse;
import com.procesos.concesionario.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userServiceImpl;
    private ApiResponse apiResponse;
    @GetMapping(value = "/{id}")
    public ResponseEntity getById(@PathVariable(name = "id") Long id){
        try{
            apiResponse = new ApiResponse(Constants.REGISTER_FOUND,userServiceImpl.getUserById(id));
            return new ResponseEntity(apiResponse, HttpStatus.OK);
        }catch(Exception e){
            apiResponse = new ApiResponse(Constants.REGISTER_NOT_FOUND, e.getMessage());
            return new ResponseEntity(apiResponse, HttpStatus.BAD_REQUEST);
        }

    }
    @PostMapping(value = "")
    public ResponseEntity createUser(@RequestBody User user){
        try{
            apiResponse = new ApiResponse(Constants.REGISTER_CREATED,userServiceImpl.createUser(user));
            return new ResponseEntity(apiResponse, HttpStatus.CREATED);
        }catch(Exception e){
            apiResponse = new ApiResponse(Constants.REGISTER_NOT_CREATED, e.getMessage());
            return new ResponseEntity(apiResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "")
    public ResponseEntity getAll(){
        try{
            apiResponse = new ApiResponse(Constants.REGISTERS_FOUND, userServiceImpl.allUsers());
            return new ResponseEntity(apiResponse, HttpStatus.OK);
        }catch(Exception e){
            apiResponse = new ApiResponse(Constants.REGISTERS_NOT_FOUND, e.getMessage());
            return new ResponseEntity(apiResponse, HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping(value="/{id}")
    public ResponseEntity updateUser(@PathVariable(name="id")Long id,@RequestBody User user) {
        try {
            apiResponse = new ApiResponse(Constants.REGISTER_UPDATED, userServiceImpl.updateUser(id, user));
            return new ResponseEntity(apiResponse, HttpStatus.OK);
        } catch (Exception e) {
            apiResponse = new ApiResponse(Constants.REGISTER_NOT_UPDATED, e.getMessage());
            return new ResponseEntity(apiResponse, HttpStatus.BAD_REQUEST);
        }
    }
}
