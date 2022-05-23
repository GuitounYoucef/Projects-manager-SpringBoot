package com.SoftwareArt.ProjectsManager.Security.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.SoftwareArt.ProjectsManager.Security.Jwt.JwtProvider;
import com.SoftwareArt.ProjectsManager.Security.Models.AuthenticationResponse;
import com.SoftwareArt.ProjectsManager.Security.Models.LoginRequest;

@Service
public class AuthService {
	
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private JwtProvider jwtProvider;	
    
    @Autowired
    private MyuserDetailsService myuserDetailsService;
    
	
    public AuthenticationResponse login(LoginRequest loginRequest) throws Exception {
    	//1
    	try {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword()));
    	}
    	catch (BadCredentialsException e) {
			throw new Exception("incorrect username or password",e);
		}
    	//2
    	final UserDetails userDetails =myuserDetailsService.loadUserByUsername(loginRequest.getUsername()); 	   	
        //3
        String authenticationToken=jwtProvider.generateToken(userDetails);
        return new AuthenticationResponse(authenticationToken, loginRequest.getUsername());
        
    }
}
