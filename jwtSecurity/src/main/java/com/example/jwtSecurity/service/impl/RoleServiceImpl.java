package com.example.jwtSecurity.service.impl;

import com.example.jwtSecurity.entity.Role;
import com.example.jwtSecurity.repo.RoleRepo;
import com.example.jwtSecurity.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepo roleRepo;

    @Override
    public Role createNewRole(Role role) {
        return roleRepo.save(role);
    }
}
