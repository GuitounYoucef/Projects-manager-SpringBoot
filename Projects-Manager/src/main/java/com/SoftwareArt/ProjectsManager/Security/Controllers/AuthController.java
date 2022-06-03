package com.SoftwareArt.ProjectsManager.Security.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SoftwareArt.ProjectsManager.Security.Models.AuthenticationResponse;
import com.SoftwareArt.ProjectsManager.Security.Models.LoginRequest;
import com.SoftwareArt.ProjectsManager.Security.Models.RegisterRequest;
import com.SoftwareArt.ProjectsManager.Security.Models.Users;

import com.SoftwareArt.ProjectsManager.Security.Services.UserService;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/test")
	String test() {
		return "Auth work";
	}
	
	@PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) throws Exception {
		System.out.println(userService.login(loginRequest));
		return userService.login(loginRequest);
		
    }
	
	
}
