package com.example.HopitalPlanningProject;

import com.example.HopitalPlanningProject.model.*;
import com.example.HopitalPlanningProject.services.ContratService;
import com.example.HopitalPlanningProject.services.InterdictionPrecedentService;
import com.example.HopitalPlanningProject.services.PersonneService;
import com.example.HopitalPlanningProject.services.RoulementGeneratorService;
import com.example.HopitalPlanningProject.services.RoulementService;
import com.example.HopitalPlanningProject.services.ShiftPosteService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataInitializer {

	@Bean
	public CommandLineRunner populateDatabase(ShiftPosteService shiftPosteService,
											  PersonneService personneService,
											  ContratService contratService,
											  InterdictionPrecedentService interdictionPrecedentService,
											  RoulementGeneratorService roulementGeneratorService,
											  RoulementService roulementService) {
		return args -> {
			// Remplissage de la table ShiftPoste
			if (shiftPosteService.getAllShifts().isEmpty()) {
				// Création de 4 shifts pour offrir plusieurs options
				ShiftPoste matin = new ShiftPoste();
				matin.setTravail(true);
				matin.setTag("MAT");
				matin.setType("Matin");
				matin.setPoste("Service A");
				shiftPosteService.createShift(matin);

				ShiftPoste apresMidi = new ShiftPoste();
				apresMidi.setTravail(true);
				apresMidi.setTag("APR");
				apresMidi.setType("Après-midi");
				apresMidi.setPoste("Service A");
				shiftPosteService.createShift(apresMidi);

				ShiftPoste soir = new ShiftPoste();
				soir.setTravail(true);
				soir.setTag("SOI");
				soir.setType("Soir");
				soir.setPoste("Service A");
				shiftPosteService.createShift(soir);

				ShiftPoste nuit = new ShiftPoste();
				nuit.setTravail(true);
				nuit.setTag("NUI");
				nuit.setType("Nuit");
				nuit.setPoste("Service A");
				shiftPosteService.createShift(nuit);

				System.out.println("Table ShiftPoste initialisée avec 4 shifts.");
			} else {
				System.out.println("Table ShiftPoste déjà peuplée.");
			}

			// Remplissage des contrats et personnes
			if (personneService.getAllPersonnes().isEmpty()) {
				// Création d'un contrat 100%
				Contrat contrat = new Contrat();
				contrat.setPourcentageTravail(100.0);
				contrat.setDescriptionContrat("Contrat 100%");

				// Génération d'un roulement (par exemple, de 2 semaines) pour ce contrat
				// La méthode generateRoulement() lit la taille (en semaines) depuis l'objet Roulement.
				Roulement roulement = roulementGeneratorService.generateRoulement();
				// Affectation du roulement au contrat (relation OneToOne : Contrat.roulement ne doit pas être null)
				contrat.setRoulement(roulement);
				contrat = contratService.saveContrat(contrat);

				// Création de deux personnes associées à ce contrat
				Personne p1 = new Personne();
				p1.setNom("Dupont");
				p1.setPrenom("Jean");
				p1.setActif(true);
				p1.setContrat(contrat);
				personneService.savePersonne(p1);

				Personne p2 = new Personne();
				p2.setNom("Martin");
				p2.setPrenom("Marie");
				p2.setActif(true);
				p2.setContrat(contrat);
				personneService.savePersonne(p2);

				System.out.println("Tables Contrat et Personne initialisées.");
			} else {
				System.out.println("Table Personne déjà peuplée.");
			}

			// Remplissage de la table InterdictionPrecedent
			if (interdictionPrecedentService.getAllInterdictions().isEmpty()) {
				// Exemple : interdire qu'un shift SOI (Soir) soit suivi d'un shift MAT (Matin)
				List<ShiftPoste> shifts = shiftPosteService.getAllShifts();
				Integer idSoir = null;
				Integer idMatin = null;
				for (ShiftPoste s : shifts) {
					if ("SOI".equals(s.getTag())) {
						idSoir = s.getIdShift();
					} else if ("MAT".equals(s.getTag())) {
						idMatin = s.getIdShift();
					}
				}
				if (idSoir != null && idMatin != null) {
					InterdictionPrecedent interdiction = new InterdictionPrecedent();
					InterdictionPrecedentId interdictionId = new InterdictionPrecedentId();
					interdictionId.setIdShift(idSoir);
					interdictionId.setIdShift1(idMatin);
					interdiction.setId(interdictionId);
					interdictionPrecedentService.createInterdiction(interdiction);
					System.out.println("InterdictionPrecedent (SOI->MAT) initialisée.");
				}
				// Vous pouvez ajouter d'autres interdictions ici pour tester d'autres cas.
			} else {
				System.out.println("Table InterdictionPrecedent déjà peuplée.");
			}
		};
	}
}
