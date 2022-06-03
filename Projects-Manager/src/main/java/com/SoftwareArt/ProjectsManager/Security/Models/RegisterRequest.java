package com.SoftwareArt.ProjectsManager.Security.Models;

public class RegisterRequest {
	private String userName;
	private String password;
	private String role;
	private Boolean accountStatus;

	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
		}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public RegisterRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Boolean getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(Boolean accountStatus) {
		this.accountStatus = accountStatus;
	}
	public RegisterRequest(String userName, String password, String role, Boolean accountStatus) {
		super();
		this.setUserName(userName);
		this.password = password;
		this.role = role;
		this.accountStatus = accountStatus;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	

	


	

}