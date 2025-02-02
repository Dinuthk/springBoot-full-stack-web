package com.example.jwtSecurity.service;

import com.example.jwtSecurity.entity.User;

public interface UserService {
    User registerNewuser(User user);
    public void initRoleAndUser();
}
