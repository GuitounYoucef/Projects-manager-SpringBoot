package com.SoftwareArt.ProjectsManager.Security.Jwt;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;



@Service
public class JwtProvider {
    
	private String secret_key="welcome";
	
    public String getUsernameFromJWT(String token) {
    	return extractClaimFromToken(token, Claims::getSubject);
    }
    
// Extract Claims (getExpiration, getSubject, ....)     
    public <T> T extractClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret_key).parseClaimsJws(token).getBody();
	}     

// Generation-------------------------------------------------------------
    public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return CreateToken(claims, userDetails.getUsername());
		
    /*    return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .signWith(SignatureAlgorithm.HS512, secret_key)
                .compact();*/
   
    }
    
    public String CreateToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() * 1000*60*60*10))
				.signWith(SignatureAlgorithm.HS512, secret_key).compact();
	}
    
// Validation-------------------------------------------------------------
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username=getUsernameFromJWT(token);
        return (username.equals(userDetails.getUsername()));
    }
    // Extract expiration date *******************************************
    public Date extractExpiration(String token) {
    	return extractClaimFromToken(token, Claims::getExpiration);
    	
    }  
	//*********************************************************************
    
    
}