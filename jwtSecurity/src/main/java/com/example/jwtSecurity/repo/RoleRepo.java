package com.example.jwtSecurity.repo;

import com.example.jwtSecurity.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface RoleRepo extends JpaRepository<Role,String> {
}
