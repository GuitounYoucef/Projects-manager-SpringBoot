package com.SoftwareArt.ProjectsManager.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="Articles")
@Setter
@Getter

public class Article {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Long id;
	private Long NumArticle;
	private String NomArticle;
	public Article() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Article(Long id, Long numArticle, String nomArticle) {
		super();
		this.id = id;
		NumArticle = numArticle;
		NomArticle = nomArticle;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getNumArticle() {
		return NumArticle;
	}
	public void setNumArticle(Long numArticle) {
		NumArticle = numArticle;
	}
	public String getNomArticle() {
		return NomArticle;
	}
	public void setNomArticle(String nomArticle) {
		NomArticle = nomArticle;
	}
	
	

	
}
