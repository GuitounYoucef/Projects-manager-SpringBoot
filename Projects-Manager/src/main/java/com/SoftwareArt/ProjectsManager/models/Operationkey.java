package com.SoftwareArt.ProjectsManager.models;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Operationkey implements Serializable{

	private Long Annee;
	private Long Num;
	private Long NumOpr;

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
	public Long getNumOpr() {
		return NumOpr;
	}
	public void setNumOpr(Long numOpr) {
		NumOpr = numOpr;
	}
	public Operationkey(Long annee, Long num, Long numOpr) {
		super();
		Annee = annee;
		Num = num;
		NumOpr = numOpr;
	}
	public Operationkey() {
		super();
		// TODO Auto-generated constructor stub
	}

}
