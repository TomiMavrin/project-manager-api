package com.tomimavrin.projectmanager.controller;

import com.tomimavrin.projectmanager.model.User;
import com.tomimavrin.projectmanager.response.Response;
import com.tomimavrin.projectmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AuthController {
    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public Response login(){
        Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
        Optional<User> response = userService.getUser(auth.getName());
        if(response.isEmpty()){
            return new Response("failure", "Invalid login info.");
        }
        else{
            return new Response("success", response);
        }
    }

    @PostMapping("/register")
    public Response register(@RequestBody User user){
        try {
            userService.createUser(user);
            return new Response("success", "User registered successfully");
        }
        catch (Exception e){
            return new Response("failure", e.toString());
        }
    }

}
