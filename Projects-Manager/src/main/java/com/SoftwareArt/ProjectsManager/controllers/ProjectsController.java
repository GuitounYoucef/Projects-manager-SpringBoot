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
	private ProjectsRepository projectRepository;
	@Autowired
	private ArticlesRepository articleRepository;
	@Autowired
	private ChapitreRepository chapitreRepository;
	@Autowired
	private OperationProjectRepository operationProjectRepository;
	

	

	
	@GetMapping("/testproject")
	public String getmessage() {
		return "Test Blog message work!!";
	}
	

	@GetMapping("/projectoperations/{annee}/{num}")
	public List<Operationproject> getAllProjectOperations(@PathVariable Long annee,@PathVariable Long num){
		return operationProjectRepository.getListOperation(annee,num);
	}
	

	@PostMapping("/projectoperations")
	public Operationproject createProjectOperation(@RequestBody Operationproject operationproject) {
    	Long recordCount;
    	recordCount=operationProjectRepository.getRecordCountByAnneeNum(operationproject.getOperationkey().getAnnee(),operationproject.getOperationkey().getNum());
    	operationproject.IncNumOpr(recordCount);
		return operationProjectRepository.save(operationproject);
	}
	
	@PutMapping("/projectoperations/{annee}/{num}/{numopr}")
	public Operationproject updateOperation(@RequestBody Operationproject opration,@PathVariable Long annee,@PathVariable Long num,@PathVariable Long numopr) {
		Operationproject opr=operationProjectRepository.getOperation(annee,num,numopr).orElseThrow(()->new ResourceNotFoundException("Project not found"+annee+" "+num));
		
		opr.setAncienMontant(opration.getAncienMontant());
		opr.setDescriptionOpr(opration.getDescriptionOpr());
		opr.setMontantOpr(opration.getMontantOpr());
		opr.setNouvMontant(opration.getNouvMontant());
		opr.setTypeOpr(opration.getTypeOpr());

		return operationProjectRepository.save(opr);
	}
	
	@DeleteMapping("/projectoperations/{annee}/{num}/{numopr}")
	public void deleteOperation(@PathVariable Long annee,@PathVariable Long num,@PathVariable Long numopr)
	{
		operationProjectRepository.deleteOperation(annee, num, numopr);
	}
	
//------------------------------------------------------------------------	
	

	@GetMapping("/projects")
	public List<Projects> getAllProjects(){
		return projectRepository.findAll();
	}
	
	@GetMapping("/projects/{annee}")
	public List<Projects> getProjectsByYear(@PathVariable Long annee){
		return projectRepository.findProjectsByYear(annee);
	}
	

	@PostMapping("/projects")
	public Projects createProject(@RequestBody Projects project) {
	
    	long recordCount;   	
    	recordCount=projectRepository.getRecordCountByYear(project.getProjectkey().getAnnee());
    	project.IncNumProject(recordCount);
		return projectRepository.save(project);	
	}
	

	@GetMapping("/projects/{annee}/{num}")
	public ResponseEntity<Projects> getPostById(@PathVariable Long annee,@PathVariable Long num) {
		Projects p=projectRepository.findByAnneeNum(annee,num).orElseThrow(()->new ResourceNotFoundException("Project not found"+annee+" "+num)); 
		return ResponseEntity.ok(p);
	}
	
	@PutMapping("/projects/{annee}/{num}")
	public Projects updateProject(@RequestBody Projects project,@PathVariable Long annee,@PathVariable Long num) {
		Projects p=projectRepository.findByAnneeNum(annee,num).orElseThrow(()->new ResourceNotFoundException("Project not found"+annee+" "+num));
		
		p.setNomProjet(project.getNomProjet());
		p.setCredit(project.getCredit());
		p.setMontant(project.getMontant());
		p.setNumArticle(project.getNumArticle());
		p.setNumChapitre(project.getNumChapitre());
		p.setNumSousChapitre(project.getNumSousChapitre());

		return projectRepository.save(p);
	}
	
	@DeleteMapping("/projects/{annee}/{num}")
	void deleteProject(@PathVariable Long annee,@PathVariable Long num) {
		projectRepository.deleteProject(annee, num);
		operationProjectRepository.deleteAllOperations(annee, num);
	}

//------------------------------------------------------------------------	   

	@GetMapping("/chapitres")
	public List<Chapitres> getAllChapitres(){
		return chapitreRepository.findAllChapitres();
	}
	

	@GetMapping("/distchapitres")
	public List<ChapitreName> getDistictChapitres(){
		return chapitreRepository.findDistinctChapitres();
	}
	

	@PostMapping("/chapitres")
	public Chapitres createCahpitre(@RequestBody Chapitres chapitre) {
		return chapitreRepository.save(chapitre);	
	}
    
//	@CrossOrigin(origins = "http://localhost:4200")
//	@GetMapping("/souschapitres/{chapitre}")
//	public List<String> getSousChapitres(@RequestBody String chapitre){
//		return chapitreRepository.findAllSouscahpitres(chapitre);
//	}    
    
    

	@GetMapping("/articles")
	public List<Article> getAllArticles(){
		return articleRepository.findAll();
	}
	

	@PostMapping("/articles")
	public Article createCahpitre(@RequestBody Article article) {
		return articleRepository.save(article);	
	}    
}
