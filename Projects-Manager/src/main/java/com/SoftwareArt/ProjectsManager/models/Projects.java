package com.SoftwareArt.ProjectsManager.models;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="Projects")
@Setter
@Getter


public class Projects {
	
	@Id
	@EmbeddedId
    private Projectkey projectkey;
 
	private String NomProjet;
	private Long NumChapitre;
	private Long NumSousChapitre;
	private Long NumArticle;
	private Float Montant;
	private Float Reste;
	private Float Credit;	

	public void IncNumProject(Long num) {
		this.projectkey.setNum(num+1);
	}	
	
	public Projectkey getProjectkey() {
		return projectkey;
	}
	public void setProjectkey(Projectkey projectkey) {
		this.projectkey = projectkey;
	}
	public String getNomProjet() {
		return NomProjet;
	}
	public void setNomProjet(String nomProjet) {
		NomProjet = nomProjet;
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
	public Long getNumArticle() {
		return NumArticle;
	}
	public void setNumArticle(Long numArticle) {
		NumArticle = numArticle;
	}
	public Float getMontant() {
		return Montant;
	}
	public void setMontant(Float montant) {
		Montant = montant;
	}
	public Float getReste() {
		return Reste;
	}
	public void setReste(Float reste) {
		Reste = reste;
	}
	public Float getCredit() {
		return Credit;
	}
	public void setCredit(Float credit) {
		Credit = credit;
	}
	public Projects(Projectkey projectkey, String nomProjet, Long numChapitre, Long numSousChapitre, Long numArticle,
			Float montant, Float reste, Float credit) {
		super();
		this.projectkey = projectkey;
		NomProjet = nomProjet;
		NumChapitre = numChapitre;
		NumSousChapitre = numSousChapitre;
		NumArticle = numArticle;
		Montant = montant;
		Reste = reste;
		Credit = credit;
	}
	public Projects() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	


	
	

}
