package com.SoftwareArt.ProjectsManager.controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
@RequestMapping("/projectsmanager/reports")
@CrossOrigin
public class EngagementReportController {
	
	
	@Autowired
	private OperationProjectRepository operationProjectRepository;
	@Autowired
	private ProjectsRepository projectRepository;
	@Autowired
	private ArticlesRepository articleRepository;
	@Autowired
	private ChapitreRepository chapitreRepository;
	
	
	
	@GetMapping("/pdf/{annee}/{num}/{numopr}")
	public ResponseEntity<byte[]> generatePDF(@PathVariable Long annee,@PathVariable Long num,@PathVariable Long numopr) throws FileNotFoundException, JRException {

	Projects p=projectRepository.findByAnneeNum(annee,num).orElseThrow(()->new ResourceNotFoundException("Project not found"+annee+" "+num));
	List<Operationproject> l =new ArrayList<Operationproject>();
	Operationproject opr=operationProjectRepository.getOperation(annee,num,numopr).orElseThrow(()->new ResourceNotFoundException("Operation not found"+annee+" "+num));
	
	l.add(opr);
	
	JRBeanCollectionDataSource beanCollectionDataSource= new JRBeanCollectionDataSource(l);
	Map<String, Object> parameters = new HashMap<>();
	parameters.put("ProjectName", p.getNomProjet());
	parameters.put("Nchapitre", p.getNumChapitre());
	parameters.put("Nsouschapitre", p.getNumSousChapitre());
	parameters.put("Narticle", p.getNumArticle());
	parameters.put("NumOpr", numopr);
	
	Chapitres c=chapitreRepository.findChapitre(p.getNumSousChapitre());
	Article a=articleRepository.findArticle(p.getNumArticle());
	
	parameters.put("Chapitre", p.getNumChapitre()+" "+c.getChapitre());
	parameters.put("SousChapitre", p.getNumSousChapitre()+" "+c.getSousChapitre());
	parameters.put("Article", p.getNumArticle()+" "+a.getNomArticle());
	
	JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("src/main/resources/engagement.jrxml"));

	JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, parameters, beanCollectionDataSource);

	
	byte data[] = JasperExportManager.exportReportToPdf(jasperPrint);

	System.err.println(data);

	HttpHeaders headers = new HttpHeaders();
	headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");

	return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);



	}

}
