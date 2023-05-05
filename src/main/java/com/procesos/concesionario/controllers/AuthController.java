package com.procesos.concesionario.controllers;

import com.procesos.concesionario.models.User;
import com.procesos.concesionario.services.UserService;
import com.procesos.concesionario.utils.ApiResponse;
import com.procesos.concesionario.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("auth")
public class AuthController {
    @Autowired
    private UserService userService;
    private ApiResponse apiResponse;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody User user){
        try{
            apiResponse = new ApiResponse(Constants.USER_LOGIN,userService.login(user));
            return new ResponseEntity(apiResponse, HttpStatus.OK);

        }catch (Exception e){
            apiResponse = new ApiResponse(e.getMessage(),"");
            return new ResponseEntity(apiResponse, HttpStatus.NOT_FOUND);
        }
    }
}







