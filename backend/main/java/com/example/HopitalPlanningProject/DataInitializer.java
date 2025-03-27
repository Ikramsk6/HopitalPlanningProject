package com.example.HopitalPlanningProject;

import com.example.HopitalPlanningProject.model.ShiftPoste;
import com.example.HopitalPlanningProject.services.ShiftPosteService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

	@Bean
	public CommandLineRunner populateShiftPoste(ShiftPosteService shiftPosteService) {
		return args -> {
			// Vérifier si la table est déjà remplie
			if (shiftPosteService.getAllShifts().isEmpty()) {

				// Exemple de shift "Matin"
				ShiftPoste matin = new ShiftPoste();
				matin.setTravail(true);
				matin.setTag("MAT");
				matin.setType("Matin");
				matin.setPoste("Service A"); // Adaptable selon vos besoins
				shiftPosteService.createShift(matin);

				// Exemple de shift "Après-midi"
				ShiftPoste apresMidi = new ShiftPoste();
				apresMidi.setTravail(true);
				apresMidi.setTag("APR");
				apresMidi.setType("Après-midi");
				apresMidi.setPoste("Service A");
				shiftPosteService.createShift(apresMidi);

				// Exemple de shift "Nuit"
				ShiftPoste nuit = new ShiftPoste();
				nuit.setTravail(true);
				nuit.setTag("NUI");
				nuit.setType("Nuit");
				nuit.setPoste("Service A");
				shiftPosteService.createShift(nuit);

				// Vous pouvez ajouter d'autres shifts selon vos règles et services.
				System.out.println("Table shift_poste initialisée avec succès.");
			} else {
				System.out.println("Table shift_poste déjà peuplée.");
			}
		};
	}
}
