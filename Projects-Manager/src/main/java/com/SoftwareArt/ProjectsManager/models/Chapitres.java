package com.SoftwareArt.ProjectsManager.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="chapitres")
@Setter
@Getter

public class Chapitres {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long NumChapitre;
	private Long NumSousChapitre;
	private String Chapitre;
	private String SousChapitre;
	public Chapitres() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Chapitres(Long id, Long numChapitre, Long numSousChapitre, String chapitre, String sousChapitre) {
		super();
		this.id = id;
		NumChapitre = numChapitre;
		NumSousChapitre = numSousChapitre;
		Chapitre = chapitre;
		SousChapitre = sousChapitre;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getNumChapitre() {
		return NumChapitre;
	}
	public void setNumChapitre(Long numChapitre) {
		NumChapitre = numChapitre;
	}
	public Long getNumSousChapitre() {
		return NumSousChapitre;
	}
	public void setNumSousChapitre(Long numSousChapitre) {
		NumSousChapitre = numSousChapitre;
	}
	public String getChapitre() {
		return Chapitre;
	}
	public void setChapitre(String chapitre) {
		Chapitre = chapitre;
	}
	public String getSousChapitre() {
		return SousChapitre;
	}
	public void setSousChapitre(String sousChapitre) {
		SousChapitre = sousChapitre;
	}


}
