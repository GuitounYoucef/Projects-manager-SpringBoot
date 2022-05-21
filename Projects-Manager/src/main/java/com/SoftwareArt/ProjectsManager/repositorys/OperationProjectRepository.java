package com.SoftwareArt.ProjectsManager.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.SoftwareArt.ProjectsManager.models.Operationproject;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

@Repository

public interface OperationProjectRepository extends JpaRepository<Operationproject, Long> {

	@Query(value = "select count(num_opr) as recordCount from operation_project where (annee=:year) and (num=:number)", nativeQuery = true)
	Long getRecordCountByAnneeNum(@Param("year") Long year, @Param("number")Long number);
	
	@Query(value = "select * from operation_project where (annee=:year) and (num=:number)", nativeQuery = true)
	List<Operationproject> getListOperation(@Param("year") Long year, @Param("number")Long number);
		
	@Query(value = "select * from operation_project where (annee=:year) and (num=:number)and (num_opr=:numOpr)", nativeQuery = true)
	Optional<Operationproject> getOperation(@Param("year") Long year, @Param("number")Long number, @Param("numOpr")Long numOpr);
	
	@Modifying
	@Transactional
	@Query(value = "delete from operation_project where (annee=:year) and (num=:number)and (num_opr=:numOpr)", nativeQuery = true)
	void deleteOperation(@Param("year") Long year, @Param("number")Long number, @Param("numOpr")Long numOpr);
	
	@Modifying
	@Transactional
	@Query(value = "delete from operation_project where (annee=:year) and (num=:number)", nativeQuery = true)
	void deleteAllOperations(@Param("year") Long year, @Param("number")Long number);
	
}
