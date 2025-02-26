package com.example.jwtSecurity.configuration;

import com.example.jwtSecurity.service.impl.JwtServiceImpl;
import com.example.jwtSecurity.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private JwtServiceImpl jwtService;

    //Mekt request ekak awama eka sarayak excute wenn => OncePerRequestFilter
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String reqestTokenHeader = request.getHeader("Authorization");

        String username = null;
        String jwtToken = null;

        if(reqestTokenHeader != null && reqestTokenHeader.startsWith("Bearer")){
            jwtToken = reqestTokenHeader.substring(7); //substring wlim index 1 -7 dkwa value tika ain krnw(Bearer kiyna word ek natuw only token gnn puluwn)
            try {
                username = jwtUtil.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException e){
                System.out.println("Unable to get jwt token");
            }catch (ExpiredJwtException e){
                System.out.println("Jwt token is expired");
            }
        }else {
            System.out.println("Jwt tokes is not start Bearer");
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = jwtService.loadUserByUsername(username);

            if(jwtUtil.validateToken(jwtToken,userDetails)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails,null,userDetails.getAuthorities()
                );
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication((usernamePasswordAuthenticationToken));
            }
        }
        filterChain.doFilter(request,response);
    }
}
