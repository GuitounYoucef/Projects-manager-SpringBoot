package com.SoftwareArt.ProjectsManager.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.SoftwareArt.ProjectsManager.exception.ResourceNotFoundException;
import com.SoftwareArt.ProjectsManager.models.Article;
import com.SoftwareArt.ProjectsManager.models.ChapitreName;
import com.SoftwareArt.ProjectsManager.models.Chapitres;
import com.SoftwareArt.ProjectsManager.models.Operationproject;
import com.SoftwareArt.ProjectsManager.models.Projects;
import com.SoftwareArt.ProjectsManager.repositorys.ArticlesRepository;
import com.SoftwareArt.ProjectsManager.repositorys.ChapitreRepository;
import com.SoftwareArt.ProjectsManager.repositorys.OperationProjectRepository;
import com.SoftwareArt.ProjectsManager.repositorys.ProjectsRepository;

@Service
public class ProjectsService {
	@Autowired
	private ProjectsRepository projectRepository;
	@Autowired
	private ArticlesRepository articleRepository;
	@Autowired
	private ChapitreRepository chapitreRepository;
	@Autowired
	private OperationProjectRepository operationProjectRepository;

//------------------------------------------------------------------------	
    //Projects
	public List<Projects> getAllProjects() {
		return projectRepository.findAll();
	}

	public List<Projects> getProjectsByYear(Long annee) {
		return projectRepository.findProjectsByYear(annee);
	}

	public Projects createProject(Projects project) {

		long recordCount;
		recordCount = projectRepository.getRecordCountByYear(project.getProjectkey().getAnnee());
		project.IncNumProject(recordCount);
		return projectRepository.save(project);
	}

	public ResponseEntity<Projects> getPostById(Long annee, Long num) {
		Projects p = projectRepository.findByAnneeNum(annee, num)
				.orElseThrow(() -> new ResourceNotFoundException("Project not found" + annee + " " + num));
		return ResponseEntity.ok(p);
	}

	public Projects updateProject(Projects project, Long annee, Long num) {
		Projects p = projectRepository.findByAnneeNum(annee, num)
				.orElseThrow(() -> new ResourceNotFoundException("Project not found" + annee + " " + num));

		p.setNomProjet(project.getNomProjet());
		p.setCredit(project.getCredit());
		p.setMontant(project.getMontant());
		p.setNumArticle(project.getNumArticle());
		p.setNumChapitre(project.getNumChapitre());
		p.setNumSousChapitre(project.getNumSousChapitre());

		return projectRepository.save(p);
	}

	public void deleteProject(Long annee, Long num) {
		projectRepository.deleteProject(annee, num);
		operationProjectRepository.deleteAllOperations(annee, num);
	}

//------------------------------------------------------------------------	
	// Operations

	public List<Operationproject> getAllProjectOperations(Long annee, Long num) {
		return operationProjectRepository.getListOperation(annee, num);
	}

	public Operationproject createProjectOperation(Operationproject operationproject) {
		Long recordCount;
		recordCount = operationProjectRepository.getRecordCountByAnneeNum(operationproject.getOperationkey().getAnnee(),
				operationproject.getOperationkey().getNum());
		operationproject.IncNumOpr(recordCount);
		return operationProjectRepository.save(operationproject);
	}

	public Operationproject updateOperation(Operationproject opration, Long annee, Long num, Long numopr) {
		Operationproject opr = operationProjectRepository.getOperation(annee, num, numopr)
				.orElseThrow(() -> new ResourceNotFoundException("Project not found" + annee + " " + num));

		opr.setAncienMontant(opration.getAncienMontant());
		opr.setDescriptionOpr(opration.getDescriptionOpr());
		opr.setMontantOpr(opration.getMontantOpr());
		opr.setNouvMontant(opration.getNouvMontant());
		opr.setTypeOpr(opration.getTypeOpr());

		return operationProjectRepository.save(opr);
	}

	public void deleteOperation(Long annee, Long num, Long numopr) {
		operationProjectRepository.deleteOperation(annee, num, numopr);
	}

//------------------------------------------------------------------------
	// Chapitres

	public List<Chapitres> getAllChapitres() {
		return chapitreRepository.findAllChapitres();
	}

	public List<ChapitreName> getDistictChapitres() {
		return chapitreRepository.findDistinctChapitres();
	}

	public Chapitres createCahpitre(Chapitres chapitre) {
		return chapitreRepository.save(chapitre);
	}

	public List<Article> getAllArticles() {
		return articleRepository.findAll();
	}

	public Article createCahpitre(Article article) {
		return articleRepository.save(article);
	}
}
