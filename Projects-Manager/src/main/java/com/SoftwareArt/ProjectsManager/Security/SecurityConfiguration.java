package com.SoftwareArt.ProjectsManager.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.SoftwareArt.ProjectsManager.Security.Services.MyuserDetailsService;


@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	
	// Authorization ----------------------------------------------------------------------------------
	@Override
	public void configure(HttpSecurity httpSecurity) throws Exception {
		 httpSecurity.csrf().disable()
		   .authorizeRequests()
		   .antMatchers("/auth/**")       
		   .permitAll()   
		   .anyRequest()
		   .authenticated();
		  //  .and()
		  // .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		
	}
	/*	@Bean
	JwtAuthenticationFilter jwtAuthenticationFilter() {
	    return new JwtAuthenticationFilter();
	}*/
	
//  Authentication ----------------------------------------------------------------------------------
	
	@Autowired
	UserDetailsService myuserDetailsService;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//auth.userDetailsService(myuserDetailsService).passwordEncoder(passwordEncoder());
		auth.userDetailsService(myuserDetailsService);

	}

	@Bean
	PasswordEncoder passwordEncoder() {
	    //return new BCryptPasswordEncoder();
		return NoOpPasswordEncoder.getInstance();
	}	
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
	    return super.authenticationManagerBean();
	}
	





}
