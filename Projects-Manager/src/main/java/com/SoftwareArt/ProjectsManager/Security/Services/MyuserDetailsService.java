package com.SoftwareArt.ProjectsManager.Security.Services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.SoftwareArt.ProjectsManager.Security.Models.Users;
import com.SoftwareArt.ProjectsManager.Security.Repository.UserRepository;



import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

@Service
public class MyuserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user=userRepository.findByUserName(username).orElseThrow(() ->
        new UsernameNotFoundException("No user found " + username));
        MyUserDetail myUserDetail=new MyUserDetail(user);
        return myUserDetail;
        
       
        
	}
	
	

	

}
