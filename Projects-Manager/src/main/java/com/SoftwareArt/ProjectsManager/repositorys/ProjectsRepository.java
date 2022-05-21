package com.SoftwareArt.ProjectsManager.repositorys;



import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.SoftwareArt.ProjectsManager.models.Projects;

@Repository
public interface ProjectsRepository extends JpaRepository<Projects, Long> {
	@Query(value = "select count(Num) as recordCount from projects where annee=:year", nativeQuery = true)
	Long getRecordCountByYear(@Param("year") Long year);
	
	@Query(value = "select * from projects where (annee=:year) and (num=:number)", nativeQuery = true)
	Optional<Projects> findByAnneeNum(@Param("year") Long year,@Param("number")Long number);
	
	
	@Modifying
	@Transactional
	@Query(value = "delete from projects where (annee=:year) and (num=:number)", nativeQuery = true)
	void deleteProject(@Param("year") Long year,@Param("number")Long number);

	
	@Query(value = "select * from projects where annee=:year", nativeQuery = true)	
	List<Projects> findProjectsByYear(@Param("year") Long year);
}
