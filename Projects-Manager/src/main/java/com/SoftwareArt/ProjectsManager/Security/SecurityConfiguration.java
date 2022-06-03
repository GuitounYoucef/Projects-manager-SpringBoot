package com.SoftwareArt.ProjectsManager.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.SoftwareArt.ProjectsManager.Security.Jwt.JwtAuthenticationFilter;
import com.SoftwareArt.ProjectsManager.Security.Services.MyuserDetailsService;


@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	
// Authorization ----------------------------------------------------------------------------------
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@Override
	public void configure(HttpSecurity httpSecurity) throws Exception {
		 httpSecurity.cors()
           .and()
           .csrf()
           .disable()
           .exceptionHandling()
           .and()
           .sessionManagement()
           .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
           .and()	   
 		   .authorizeRequests()	   
		   .antMatchers("/auth/login").permitAll()
		   .antMatchers("/users/**").hasRole("ADMIN")
		   .antMatchers("/projectsmanager/**").hasAnyRole("ADMIN","USER")
		   .anyRequest().authenticated();
	        httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);


		
	}

//  Authentication ----------------------------------------------------------------------------------
	
	@Autowired
	UserDetailsService myuserDetailsService;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception  {
		auth.userDetailsService(myuserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}	
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
	    return super.authenticationManagerBean();
	}
	





}
