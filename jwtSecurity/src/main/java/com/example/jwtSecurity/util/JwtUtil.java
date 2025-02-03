package com.example.jwtSecurity.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {
    private static final String SECRET_KEY = "12345678";
    private static final int TOKEN_VALIDITY = 60*60*5;

    public String getUsernameFromToken(String token){
        return getClaimFromToken(token, Claims::getSubject);
    }
    public <T> T getClaimFromToken(String token, Function<Claims,T> claimsResolver){
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJwt(token).getBody();
    }

    //token validation
    public boolean validateToken(String token, UserDetails userDetails){
        final String username = getUsernameFromToken(token);
//        if (username.equalsIgnoreCase(userDetails.getUsername())){
//            return true;
//        }else {
//            return false;
//        } mekama one line ekem liymu

        return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
    }

    //token expired or not
    public boolean isTokenExpired(String token){
        final Date expiration = getClaimFromToken(token,Claims::getExpiration);
        return expiration.before(new Date());
    }

    //genarate token
    public String genarateToken(UserDetails userDetails){
        Map<String,Object> claims = new HashMap<>();

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY*1000))
                .signWith(SignatureAlgorithm.HS512,SECRET_KEY)
                .compact();
    }
}
