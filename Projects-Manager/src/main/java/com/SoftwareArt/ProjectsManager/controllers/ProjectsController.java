package com.SoftwareArt.ProjectsManager.controllers;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SoftwareArt.ProjectsManager.repositorys.ArticlesRepository;
import com.SoftwareArt.ProjectsManager.repositorys.ChapitreRepository;
import com.SoftwareArt.ProjectsManager.repositorys.OperationProjectRepository;
import com.SoftwareArt.ProjectsManager.repositorys.ProjectsRepository;
import com.SoftwareArt.ProjectsManager.services.ProjectsService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.SoftwareArt.ProjectsManager.models.Article;
import com.SoftwareArt.ProjectsManager.models.ChapitreName;
import com.SoftwareArt.ProjectsManager.models.Chapitres;
import com.SoftwareArt.ProjectsManager.models.Operationproject;
import com.SoftwareArt.ProjectsManager.models.Projects;
import com.SoftwareArt.ProjectsManager.exception.ResourceNotFoundException;

import javax.validation.ValidationException;


@RestController
@RequestMapping("/projectsmanager/")
@CrossOrigin
public class ProjectsController {
	@Autowired
	private ProjectsService projectsService;
	

	@GetMapping("/testproject")
	public String getmessage() {
		return "Test Blog message work!!";
	}
	
//------------------------------------------------------------------------
	//Projects
	
	@GetMapping("/projects")
	public List<Projects> getAllProjects(){
		return projectsService.getAllProjects();
	}
	
	@GetMapping("/projects/{annee}")
	public List<Projects> getProjectsByYear(@PathVariable Long annee){
		return projectsService.getProjectsByYear(annee);
	}
	
	@PostMapping("/projects")
	public Projects createProject(@RequestBody Projects project) {

		return projectsService.createProject(project);	
	}

	@GetMapping("/projects/{annee}/{num}")
	public ResponseEntity<Projects> getPostById(@PathVariable Long annee,@PathVariable Long num) { 
		return projectsService.getPostById(annee,num);
	}
	
	@PutMapping("/projects/{annee}/{num}")
	public Projects updateProject(@RequestBody Projects project,@PathVariable Long annee,@PathVariable Long num) {

		return projectsService.updateProject(project,annee,num);
	}
	
	@DeleteMapping("/projects/{annee}/{num}")
	void deleteProject(@PathVariable Long annee,@PathVariable Long num) {
		
		projectsService.deleteProject(annee, num);
	}
//------------------------------------------------------------------------	
	//Operations
	
	@GetMapping("/projectoperations/{annee}/{num}")
	public List<Operationproject> getAllProjectOperations(@PathVariable Long annee,@PathVariable Long num){
		return projectsService.getAllProjectOperations(annee,num);
	}
	

	@PostMapping("/projectoperations")
	public Operationproject createProjectOperation(@RequestBody Operationproject operationproject) {
		return projectsService.createProjectOperation(operationproject);
	}
	
	@PutMapping("/projectoperations/{annee}/{num}/{numopr}")
	public Operationproject updateOperation(@RequestBody Operationproject opration,@PathVariable Long annee,@PathVariable Long num,@PathVariable Long numopr) {

		return projectsService.updateOperation(opration,annee,num,numopr);
	}
	
	@DeleteMapping("/projectoperations/{annee}/{num}/{numopr}")
	public void deleteOperation(@PathVariable Long annee,@PathVariable Long num,@PathVariable Long numopr)
	{
		projectsService.deleteOperation(annee, num, numopr);
	}	
//------------------------------------------------------------------------
	//Chapitres

	@GetMapping("/chapitres")
	public List<Chapitres> getAllChapitres(){
		return projectsService.getAllChapitres();
	}
	
	@GetMapping("/distchapitres")
	public List<ChapitreName> getDistictChapitres(){
		return projectsService.getDistictChapitres();
	}
	
	@PostMapping("/chapitres")
	public Chapitres createCahpitre(@RequestBody Chapitres chapitre) {
		return projectsService.createCahpitre(chapitre);	
	}
    
	@GetMapping("/articles")
	public List<Article> getAllArticles(){
		return projectsService.getAllArticles();
	}
	
	@PostMapping("/articles")
	public Article createCahpitre(@RequestBody Article article) {
		return projectsService.createCahpitre(article);	
	}    
}
