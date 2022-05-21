package com.SoftwareArt.ProjectsManager.Security.Models;

public class RegisterRequest {
	private String name;
	private String password;
	private String email;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public RegisterRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RegisterRequest(String name, String password, String email) {
		super();
		this.name = name;
		this.password = password;
		this.email = email;
	}


	

}