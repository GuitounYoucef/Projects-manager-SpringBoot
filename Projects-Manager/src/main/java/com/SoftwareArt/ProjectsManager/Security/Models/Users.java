package com.SoftwareArt.ProjectsManager.Security.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "userName")
	private String userName;
	@Column(name = "Password")
	private String Userpassword;
	@Column(name = "Role")
	private String Role;

	@Column(name = "accountStatus")
	private Boolean accountStatus;
	

	public Users(Long id, String userName, String userpassword, String role, Boolean accountStatus) {
		super();
		this.id = id;
		this.userName = userName;
		Userpassword = userpassword;
		Role = role;
		this.accountStatus = accountStatus;
	}
	public Boolean getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(Boolean accountStatus) {
		this.accountStatus = accountStatus;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}

	public String getUserpassword() {
		return Userpassword;
	}
	public void setUserpassword(String userpassword) {
		Userpassword = userpassword;
	}



	public String getRole() {
		return Role;
	}
	public void setRole(String role) {
		Role = role;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}