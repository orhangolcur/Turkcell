package com.turkcell.spring_starter.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.spring_starter.dto.user.LoginRequest;
import com.turkcell.spring_starter.dto.user.RegisterRequest;
import com.turkcell.spring_starter.service.UserServiceImpl;

@RequestMapping("/api/users")
@RestController
public class UserController {

    private final UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }


    @PostMapping
    public void register(@RequestBody RegisterRequest registerRequest)
    {
        this.userServiceImpl.registerUser(registerRequest);
    }

    @PostMapping("login")
    public String login(@RequestBody LoginRequest loginRequest)
    {
        return this.userServiceImpl.login(loginRequest);
    }
}
