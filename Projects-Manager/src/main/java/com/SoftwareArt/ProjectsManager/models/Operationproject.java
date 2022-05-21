package com.SoftwareArt.ProjectsManager.models;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import javax.persistence.Id;

import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="OperationProject")
@Setter
@Getter

public class Operationproject {
	
	@Id
	@EmbeddedId
	private Operationkey operationkey;
	private int TypeOpr;
	private String DescriptionOpr;
	private Float MontantOpr;
	private Float NouvMontant;
	private Float AncienMontant;
	

	public Operationproject() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Operationproject(Operationkey operationkey, int typeOpr, String descriptionOpr, Float montantOpr,
			Float nouvMontant, Float ancienMontant) {
		super();
		this.operationkey = operationkey;
		TypeOpr = typeOpr;
		DescriptionOpr = descriptionOpr;
		MontantOpr = montantOpr;
		NouvMontant = nouvMontant;
		AncienMontant = ancienMontant;
	}

	
	public void IncNumOpr(Long Num) {
		this.operationkey.setNumOpr(Num+1);
	}

	public Operationkey getOperationkey() {
		return operationkey;
	}


	public void setOperationkey(Operationkey operationkey) {
		this.operationkey = operationkey;
	}


	public int getTypeOpr() {
		return TypeOpr;
	}


	public void setTypeOpr(int typeOpr) {
		TypeOpr = typeOpr;
	}


	public String getDescriptionOpr() {
		return DescriptionOpr;
	}


	public void setDescriptionOpr(String descriptionOpr) {
		DescriptionOpr = descriptionOpr;
	}


	public Float getMontantOpr() {
		return MontantOpr;
	}


	public void setMontantOpr(Float montantOpr) {
		MontantOpr = montantOpr;
	}


	public Float getNouvMontant() {
		return NouvMontant;
	}


	public void setNouvMontant(Float nouvMontant) {
		NouvMontant = nouvMontant;
	}


	public Float getAncienMontant() {
		return AncienMontant;
	}


	public void setAncienMontant(Float ancienMontant) {
		AncienMontant = ancienMontant;
	}
	
	
}
