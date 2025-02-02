package com.example.jwtSecurity.controller;

import com.example.jwtSecurity.entity.User;
import com.example.jwtSecurity.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register-new-user")
    public User registerNewuser(@RequestBody User user){
        return userService.registerNewuser(user);
    }

    @PostConstruct  //meka nisa req enne natuwa me function ek run wenwa
    public void initRoleAndUser(){
        userService.initRoleAndUser();
    }

    @GetMapping({"for-admin"})
    public String forAdmin(){
        return "this url is only accessible to admin";
    }

    @GetMapping({"for-user"})
    public String forUser(){
        return "this url is only accessible to user";
    }
}
