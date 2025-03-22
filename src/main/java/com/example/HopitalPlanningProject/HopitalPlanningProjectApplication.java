package com.example.HopitalPlanningProject;

import com.example.HopitalPlanningProject.model.Roulement;
import com.example.HopitalPlanningProject.services.RoulementGeneratorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.example.HopitalPlanningProject.model.ShiftPoste;
import com.example.HopitalPlanningProject.services.ShiftPosteService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class HopitalPlanningProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(HopitalPlanningProjectApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(RoulementGeneratorService generatorService) {
		return (args) -> {
			for (int i = 1; i <= 10; i++) {
				try {
					Roulement roulement = generatorService.generateRoulement();
					System.out.println("----- Roulement " + i + " généré -----");
					System.out.println(roulement);
					System.out.println("-------------------------------------");
				} catch (Exception e) {
					System.out.println("Erreur lors de la génération du roulement " + i + " : " + e.getMessage());
				}
			}
		};
	}
}

