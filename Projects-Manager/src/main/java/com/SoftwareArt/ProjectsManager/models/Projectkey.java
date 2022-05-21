package com.SoftwareArt.ProjectsManager.models;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Projectkey implements Serializable {
	private Long Annee;
	private Long Num;
	public Long getAnnee() {
		return Annee;
	}
	public void setAnnee(Long annee) {
		Annee = annee;
	}
	public Long getNum() {
		return Num;
	}
	public void setNum(Long num) {
		Num = num;
	}
	public Projectkey(Long annee, Long num) {
		super();
		Annee = annee;
		Num = num;
	}
	public Projectkey() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
