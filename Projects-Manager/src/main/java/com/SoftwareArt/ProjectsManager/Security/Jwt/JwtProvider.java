package com.SoftwareArt.ProjectsManager.Security.Jwt;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;



@Service
public class JwtProvider {

    private String secret_key="welcome";

    public String generateToken(Authentication authentication) {
        User principal = (User) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(principal.getUsername())
                .signWith(SignatureAlgorithm.HS512, secret_key)
                .compact();
    }


    public boolean validateToken(String jwt) {
        Jwts.parser().setSigningKey(secret_key).parseClaimsJws(jwt);
        return true;
    }


 
    public String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret_key)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
}