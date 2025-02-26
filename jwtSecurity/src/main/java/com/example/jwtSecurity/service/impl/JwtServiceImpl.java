package com.example.jwtSecurity.service.impl;

import com.example.jwtSecurity.dto.LoginRequest;
import com.example.jwtSecurity.dto.LoginResponse;
import com.example.jwtSecurity.entity.User;
import com.example.jwtSecurity.repo.UserRepo;
import com.example.jwtSecurity.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class JwtServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private JwtUtil jwtUtil;

    private final AuthenticationManager authenticationManager;

    @Autowired
    public JwtServiceImpl(@Lazy AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findById(username).get();

        if (user !=null){
            return new org.springframework.security.core.userdetails.User(
                    user.getUserName(),
                    user.getPassword(),
                    getAthority(user)
            );
        }else {
            throw new UsernameNotFoundException("User not found with username: "+username);
        }
    }
    private Set getAthority(User user){
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
//        for (Role role: user.getRoles()){
//            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
//        }
        //using lamda function
        user.getRoles().forEach(
                role -> {
                    authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
                }
        );
        return authorities;
    }

    public LoginResponse createJwtToken(LoginRequest loginRequest) throws Exception {
        String username = loginRequest.getName();
        String userPassword = loginRequest.getPassword();

        authendicate(username,userPassword);

        UserDetails userDetails = loadUserByUsername(username);
        String newGenaratedToken = jwtUtil.genarateToken(userDetails);
        User user = userRepo.findById(username).get();

        LoginResponse loginResponse = new LoginResponse(
                user,
                newGenaratedToken
        );

        return loginResponse;
    }

    private void authendicate(String username,String userPassword) throws Exception{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,userPassword));
        }catch (BadCredentialsException e){
            throw new Exception("INVALID_CREDENTIALS",e);
        }
    }
}
