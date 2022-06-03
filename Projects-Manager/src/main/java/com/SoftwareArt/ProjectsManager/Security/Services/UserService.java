package com.SoftwareArt.ProjectsManager.Security.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.SoftwareArt.ProjectsManager.Security.Jwt.JwtProvider;
import com.SoftwareArt.ProjectsManager.Security.Models.AuthenticationResponse;
import com.SoftwareArt.ProjectsManager.Security.Models.LoginRequest;
import com.SoftwareArt.ProjectsManager.Security.Models.RegisterRequest;
import com.SoftwareArt.ProjectsManager.Security.Models.Users;
import com.SoftwareArt.ProjectsManager.Security.Repository.UserRepository;

@Service
public class UserService {
	
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private JwtProvider jwtProvider;	
    
    @Autowired
    private MyuserDetailsService myuserDetailsService;
    
	@Autowired
	private UserRepository userRepository;
	
  	
    public AuthenticationResponse login(LoginRequest loginRequest) throws Exception {
    	System.out.println("load user ...");
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
        String s=userDetails.getAuthorities().stream().map(ga->ga.getAuthority()).collect(Collectors.toList()).get(0);
        return new AuthenticationResponse(authenticationToken, userDetails.getUsername(),s);
        
    }
 
//-------------------------------------------------------------------------------------------------------------    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public void saveUser(RegisterRequest registerRequest) {
		Users user=new Users();
		user.setRole(registerRequest.getRole());
		user.setUserName(registerRequest.getUserName());
    	user.setUserpassword(passwordEncoder.encode(registerRequest.getPassword()));
    	user.setAccountStatus(registerRequest.getAccountStatus());
    	userRepository.save(user);
    }
    
    public void upadateUser(RegisterRequest registerRequest) {
		Users user=userRepository.findByUserName(registerRequest.getUserName()).orElseThrow(() ->
        new UsernameNotFoundException("No user found " + registerRequest.getUserName()));;
		user.setRole(registerRequest.getRole());
		user.setUserName(registerRequest.getUserName());
    	//user.setUserpassword(passwordEncoder.encode(registerRequest.getPassword()));
    	user.setAccountStatus(registerRequest.getAccountStatus());
    	userRepository.save(user);
    }    
    
    
}
