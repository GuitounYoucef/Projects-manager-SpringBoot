package com.SoftwareArt.ProjectsManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ProjectsManagerApplication extends SpringBootServletInitializer{
	
	@GetMapping("/")
	public String getM() {
		return "Project manager work";
	}

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ProjectsManagerApplication.class);
    }	
	
	public static void main(String[] args) {
		SpringApplication.run(ProjectsManagerApplication.class, args);
	}

}
