package com.SoftwareArt.ProjectsManager.Security.Jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.SoftwareArt.ProjectsManager.Security.Services.MyuserDetailsService;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
    @Autowired
    private JwtProvider jwtProvider;	
    
    @Autowired
    private MyuserDetailsService myuserDetailsService;	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String authorizationHeader = request.getHeader("Authorization");
		
		String jwt=null;
		String username=null;
		
		if(authorizationHeader!=null && authorizationHeader.startsWith("Bearer"))
		{
			jwt=authorizationHeader.substring(7);
			username=jwtProvider.getUsernameFromJWT(jwt);		
		}
		
		
		
		
	}

}
