package com.SoftwareArt.ProjectsManager.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.SoftwareArt.ProjectsManager.models.Article;

@Repository
public interface ArticlesRepository extends JpaRepository<Article, Long> {
	
	@Query(value = "select * FROM articles where (num_article=:numarticle) ", nativeQuery = true)
	Article findArticle(@Param("numarticle") Long numarticle);

}
