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
	@Column(name = "Email")
	private String UserEmail;
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
	public String getUserEmail() {
		return UserEmail;
	}
	public void setUserEmail(String userEmail) {
		UserEmail = userEmail;
	}

	public Users(String userName, String userpassword, String userEmail) {
		super();
		this.userName = userName;
		Userpassword = userpassword;
		UserEmail = userEmail;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}