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

    // Constructeur pour injecter les dépendances
    public PersonneService(PersonneRepository personneRepository, ContratRepository contratRepository) {
        this.personneRepository = personneRepository;
        this.contratRepository = contratRepository; // Initialisez ContratRepository
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
        // Log pour vérifier l'objet Personne reçu
        System.out.println("Personne à sauvegarder : " + personne);

        // Associe les préférences à la personne
        if (personne.getPreferences() != null) {
            for (PreferencePersonnel pref : personne.getPreferences()) {
                pref.setPersonne(personne); // Associe les préférences à la personne
            }
        }

        // Vérifie que le contrat existe et récupère-le s'il est fourni sous forme d'id
        if (personne.getContrat() != null) {
            // Log pour vérifier le contrat
            System.out.println("Contrat trouvé dans la personne : " + personne.getContrat());

            if (personne.getContrat().getIdContrat() > 0) {
                // Log pour vérifier l'id du contrat
                System.out.println("IdContrat : " + personne.getContrat().getIdContrat());

                Contrat contrat = contratRepository.findById(personne.getContrat().getIdContrat())
                        .orElseThrow(() -> new ContratNotFoundException("Contrat not found with id: " + personne.getContrat().getIdContrat()));

                // Log pour vérifier le contrat récupéré
                System.out.println("Contrat récupéré : " + contrat);

                personne.setContrat(contrat);  // Associe le contrat à la personne
            } else {
                // Log si l'id du contrat est invalide
                System.out.println("Contrat ID est invalide : " + personne.getContrat().getIdContrat());
                throw new IllegalArgumentException("Contrat ID is invalid: " + personne.getContrat().getIdContrat());
            }
        } else {
            // Log si aucun contrat n'est trouvé dans la personne
            System.out.println("Contrat est manquant dans l'objet Personne");
            throw new IllegalArgumentException("Contrat is required");
        }

        // Log pour vérifier que tout est bon avant de sauvegarder
        System.out.println("Personne prête à être sauvegardée : " + personne);

        // Sauvegarde la personne dans la base de données
        return personneRepository.save(personne);
    }


    // Méthode pour mettre à jour une personne
    public Personne updatePersonne(int id, Personne personne) {
        if (personneRepository.existsById(id)) {
            // On garantit que l'ID de la personne à mettre à jour reste inchangé
            personne.setIdPersonne(id);
            return personneRepository.save(personne);
        }
        return null; // Ou vous pouvez lancer une exception si la personne n'existe pas
    }

    // Méthode pour supprimer une personne
    public void deletePersonne(int id) {
        personneRepository.deleteById(id);
    }
}
