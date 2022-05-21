package com.SoftwareArt.ProjectsManager.Security.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name="roles")
@Setter
@Getter
public class Roles {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "RoleName")
	private String RoleName;
	
//------------------------------	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRoleName() {
		return RoleName;
	}
	public void setRoleName(String roleName) {
		RoleName = roleName;
	}
	public Roles() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Roles(String roleName) {
		super();
		RoleName = roleName;
	}
	

}