package com.example.HopitalPlanningProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.HopitalPlanningProject.services.ShiftService;
/**
@SpringBootApplication
public class HopitalPlanningProjectApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(HopitalPlanningProjectApplication.class, args);
		ShiftService shiftService = context.getBean(ShiftService.class);
		shiftService.ajouterShiftsDepuisConsole();
	}
}
*/

@SpringBootApplication
public class HopitalPlanningProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(HopitalPlanningProjectApplication.class, args);
	}

}