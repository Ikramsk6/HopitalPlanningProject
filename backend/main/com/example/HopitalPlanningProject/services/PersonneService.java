package com.example.HopitalPlanningProject.services;

import com.example.HopitalPlanningProject.erreurs.ContratNotFoundException;
import com.example.HopitalPlanningProject.model.Contrat;
import com.example.HopitalPlanningProject.model.Personne;
import com.example.HopitalPlanningProject.model.PreferencePersonnel;
import com.example.HopitalPlanningProject.repositories.PersonneRepository;
import com.example.HopitalPlanningProject.repositories.ContratRepository; // Ajoutez l'import pour ContratRepository
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service pour gérer les personnes.
 */
@Service
public class PersonneService {
    private final PersonneRepository personneRepository;
    private final ContratRepository contratRepository; // Ajoutez une référence à ContratRepository
    private final ContratService contratService; // Déclare ContratService comme attribut


    // Constructeur pour injecter les dépendances
    public PersonneService(PersonneRepository personneRepository, ContratRepository contratRepository, ContratService contratService) {
        this.personneRepository = personneRepository;
        this.contratRepository = contratRepository; // Initialisez ContratRepository
        this.contratService = contratService;
    }

    // Récupère toutes les personnes
    public List<Personne> getAllPersonnes() {
        return personneRepository.findAll();
    }

    // Récupère une personne par son ID
    public Optional<Personne> getPersonneById(int id) {
        return personneRepository.findById(id);
    }

    // Sauvegarde une personne
    public Personne savePersonne(Personne personne) {
        // Vérifie si les préférences sont bien associées
        if (personne.getPreferences() != null) {
            for (PreferencePersonnel pref : personne.getPreferences()) {
                pref.setPersonne(personne); // Associe les préférences à la personne
            }
        }

        // Vérification du contrat
        if (personne.getContrat() != null) {
            if (personne.getContrat().getIdContrat() > 0) {
                Contrat contrat = contratRepository.findById(personne.getContrat().getIdContrat())
                        .orElseThrow(() -> new ContratNotFoundException("Contrat not found with id: " + personne.getContrat().getIdContrat()));

                personne.setContrat(contrat);  // Associe le contrat à la personne
            } else {
                throw new IllegalArgumentException("Contrat ID is invalid: " + personne.getContrat().getIdContrat());
            }
        } else {
            personne.setContrat(contratRepository.getReferenceById(1));
        }

        return personneRepository.save(personne);
    }


    // Méthode pour mettre à jour une personne
    public Personne updatePersonne(int id, Personne personne) {
        if (contratRepository.existsById(id)) {
            // On garantit que l'ID de la personne à mettre à jour reste inchangé
            Contrat contrat = contratService.getContratById(id); // Nous récupérons ou mettons à jour le contrat

            // Associer le contrat à la personne
            personne.setContrat(contrat);
            return personneRepository.save(personne);
        }
        return null; // Ou vous pouvez lancer une exception si la personne n'existe pas
    }

    // Méthode pour supprimer une personne
    public void deletePersonne(int id) {
        personneRepository.deleteById(id);
    }

    public void deleteAllPersonnes() {
        personneRepository.deleteAll();  // Cette méthode supprimera toutes les personnes
    }

}
