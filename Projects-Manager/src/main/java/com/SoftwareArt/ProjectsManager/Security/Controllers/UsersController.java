package com.SoftwareArt.ProjectsManager.Security.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SoftwareArt.ProjectsManager.Security.Models.RegisterRequest;
import com.SoftwareArt.ProjectsManager.Security.Models.Users;
import com.SoftwareArt.ProjectsManager.Security.Repository.UserRepository;
import com.SoftwareArt.ProjectsManager.Security.Services.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/users/")

public class UsersController {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	UserService userService;	


	@GetMapping("/all")
	public List<Users> getAllUsers(){
		return userRepository.findAll();
	}
	
	
	@PostMapping("/saveuser")
	public ResponseEntity<?> saveUser(@RequestBody RegisterRequest registerRequest) {
		System.out.println("saving user ...");
		userService.saveUser(registerRequest);
		return new ResponseEntity<>(HttpStatus.OK);
		}

	@PutMapping("/updateuser")
	public ResponseEntity<?> updateUser(@RequestBody RegisterRequest registerRequest) {
		System.out.println("saving user ...");
		userService.upadateUser(registerRequest);
		return new ResponseEntity<>(HttpStatus.OK);
		}	
}
