package com.example.jwtSecurity.controller;

import com.example.jwtSecurity.dto.LoginRequest;
import com.example.jwtSecurity.dto.LoginResponse;
import com.example.jwtSecurity.service.JwtService;
import com.example.jwtSecurity.service.impl.JwtServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogicController {

    @Autowired
    private JwtServiceImpl jwtServiceImpl;

    //authendication==login
    @PostMapping("/authentication")
    public LoginResponse createJwtAndlogin(@RequestBody LoginRequest loginRequest) throws Exception{
        return jwtServiceImpl.createJwtToken(loginRequest);
    }
}
