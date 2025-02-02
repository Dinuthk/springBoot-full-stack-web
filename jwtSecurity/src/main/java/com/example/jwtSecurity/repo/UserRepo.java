package com.example.jwtSecurity.repo;

import com.example.jwtSecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,String> {
}
