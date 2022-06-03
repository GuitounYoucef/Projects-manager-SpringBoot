package com.SoftwareArt.ProjectsManager.Security.Services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.SoftwareArt.ProjectsManager.Security.Models.Users;

public class MyUserDetail implements UserDetails{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Users user;

    public MyUserDetail(Users user){
        this.user = user;
    }	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + this.user.getRole());
            authorities.add(authority);
  
        return authorities;
	}
	

	@Override
	public String getPassword() {
		return this.user.getUserpassword();
	}

	@Override
	public String getUsername() {

		return this.user.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {

		return true;
	}

	@Override
	public boolean isAccountNonLocked() {

		return this.user.getAccountStatus();
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	public boolean isEnabled() {

		return true;
	}

}
