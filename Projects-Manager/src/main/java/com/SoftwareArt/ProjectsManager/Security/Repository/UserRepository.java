package com.SoftwareArt.ProjectsManager.Security.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SoftwareArt.ProjectsManager.Security.Models.Users;


@Repository

public interface UserRepository extends JpaRepository<Users, Long>{
	Optional<Users> findByUserName(String username);
}
