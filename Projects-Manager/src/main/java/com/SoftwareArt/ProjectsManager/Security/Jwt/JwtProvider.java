package com.SoftwareArt.ProjectsManager.Security.Jwt;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
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
		return CreateToken(claims, userDetails.getUsername(),userDetails.getAuthorities());
   
    }
    
    public String CreateToken(Map<String, Object> claims, String subject,Collection<? extends GrantedAuthority> authorities ) {


		return Jwts.builder().
				setClaims(claims)
				.setSubject(subject)
				.claim("roles", authorities.stream().map(ga->ga.getAuthority()).collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000*60*10))
				.signWith(SignatureAlgorithm.HS512, secret_key).compact();
	}
    
// Validation-------------------------------------------------------------
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username=getUsernameFromJWT(token);
        return (username.equals(userDetails.getUsername())) && (!isExpiredToken(token));
    }
    //expiration date *******************************************
    private boolean isExpiredToken(String token)
    
    {
    	Date d=new Date(System.currentTimeMillis());
    	return  extractExpiration(token).before(d);
    }
    public Date extractExpiration(String token) {
    	return extractClaimFromToken(token, Claims::getExpiration);
    	
    }  
	//*********************************************************************
    
    
}