package com.SoftwareArt.ProjectsManager.repositorys;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.SoftwareArt.ProjectsManager.models.ChapitreName;
import com.SoftwareArt.ProjectsManager.models.Chapitres;

public interface ChapitreRepository extends JpaRepository<Chapitres, Long> {

	
	@Query(value = "select * FROM chapitres", nativeQuery = true)
	List<Chapitres> findAllChapitres();
	
	@Query(value = "select * FROM chapitres where (num_sous_chapitre=:numchp) ", nativeQuery = true)
	Chapitres findChapitre(@Param("numchp") Long numchp);	
	
	@Query(value = "select distinct chapitre,num_chapitre as numChapitre FROM chapitres", nativeQuery = true)
	List<ChapitreName> findDistinctChapitres();
}
