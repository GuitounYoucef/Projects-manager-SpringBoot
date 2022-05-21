package com.SoftwareArt.ProjectsManager.Security.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SoftwareArt.ProjectsManager.Security.Models.AuthenticationResponse;
import com.SoftwareArt.ProjectsManager.Security.Models.LoginRequest;
import com.SoftwareArt.ProjectsManager.Security.Services.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	AuthService authService;
	
	@PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
		return authService.login(loginRequest);
    }
}
