package com.example.HopitalPlanningProject;

import com.example.HopitalPlanningProject.services.ContratService;
import com.example.HopitalPlanningProject.services.RoulementGeneratorService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

/**
 * Classe principale de l'application Hospital Planning.
 * Elle démarre l'application Spring Boot et, via un CommandLineRunner,
 * génère et affiche plusieurs roulements pour tester la génération.
 */
@SpringBootApplication
public class HopitalPlanningProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(HopitalPlanningProjectApplication.class, args);
	}

	/**
	 * CommandLineRunner qui génère plusieurs roulements valides,
	 * initialise les contrats par défaut (100% et 50%),
	 * et affiche une dizaine d'exemples pour vérification.
	 */
	@Bean
	public CommandLineRunner demo(RoulementGeneratorService generatorService, ContratService contratService) {
		return args -> {
			// Génère 50 roulements valides
			generatorService.generateMultipleRoulements(50);

			// Initialise les contrats par défaut (100% et 50%)
			contratService.initializeDefaultContrats();
		};
	}
}
