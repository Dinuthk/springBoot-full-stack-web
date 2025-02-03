package com.example.jwtSecurity.service.impl;

import com.example.jwtSecurity.entity.Role;
import com.example.jwtSecurity.entity.User;
import com.example.jwtSecurity.repo.RoleRepo;
import com.example.jwtSecurity.repo.UserRepo;
import com.example.jwtSecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User registerNewuser(User user) {
        return userRepo.save(user);
    }

    public void initRoleAndUser(){

        Role adminRole = new Role();
        if (!roleRepo.existsById("Admin")){
            adminRole.setRoleName("Admin");
            adminRole.setRoleDescription("initial Admin role created");
            roleRepo.save(adminRole);
        }
        Role userRole = new Role();
        if (!roleRepo.existsById("User")){
            userRole.setRoleName("User");
            userRole.setRoleDescription("initial User role created");
            roleRepo.save(userRole);
        }
        if (userRepo.count()<2){ //kalim user kenek hdla nattn 2 danne role 2k tyna user kenek hdnn oni nisa
            User initUser = new User();
            initUser.setUserName("Admin1");
            initUser.setAge(20);
            initUser.setPassword(getEncodePassword("admin123"));

            Set<Role> adminRoles = new HashSet<>();
            adminRoles.add(adminRole); //add admin role

            initUser.setRoles(adminRoles);
            userRepo.save(initUser); //save user
        }
        if (userRepo.count()<2){
            User initUserTwo = new User();
            initUserTwo.setUserName("User1");
            initUserTwo.setAge(20);
            initUserTwo.setPassword(getEncodePassword("user123"));

            Set<Role> userRoles = new HashSet<>();
            userRoles.add(userRole);

            initUserTwo.setRoles(userRoles);
            userRepo.save(initUserTwo);
        }
    }
    public String getEncodePassword(String password){
        return passwordEncoder.encode(password);
    }

}
