package com.example.jwtSecurity.service;

import com.example.jwtSecurity.entity.Role;
import com.example.jwtSecurity.repo.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;

public interface RoleService {
    Role createNewRole(Role role);
}
