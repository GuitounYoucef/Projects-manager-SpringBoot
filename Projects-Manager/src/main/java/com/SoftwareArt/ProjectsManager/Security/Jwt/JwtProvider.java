package com.SoftwareArt.ProjectsManager.Security.Jwt;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;



@Service
public class JwtProvider {

    private String secret_key="welcome";

    public String generateToken(UserDetails userDetails) {

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
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