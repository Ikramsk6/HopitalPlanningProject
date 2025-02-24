package com.example.HopitalPlanningProject.services;

import com.example.HopitalPlanningProject.DTO.PersonneDTO;
import com.example.HopitalPlanningProject.models.Equipe;
import com.example.HopitalPlanningProject.models.Personne;
import com.example.HopitalPlanningProject.repositories.EquipeRepository;
import com.example.HopitalPlanningProject.repositories.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class PersonneService {
    private final PersonneRepository personneRepository;
    private final EquipeRepository equipeRepository;
    private static final Logger logger = Logger.getLogger(PersonneService.class.getName());

    @Autowired
    public PersonneService(PersonneRepository personneRepository, EquipeRepository equipeRepository) {
        this.personneRepository = personneRepository;
        this.equipeRepository = equipeRepository;
    }

    public List<Personne> getAllPersonnes() {
        return personneRepository.findAll();
    }

    public Optional<Personne> getPersonneById(Long id) {
        return personneRepository.findById(id);
    }

    public Personne createPersonne(Personne personne) {
        return personneRepository.save(personne);
    }

    public Personne updatePersonne(Long id, Personne personneDetails) {
        return personneRepository.findById(id).map(personne -> {
            personne.setNom(personneDetails.getNom());
            personne.setTypeContrat(personneDetails.getTypeContrat());
            return personneRepository.save(personne);
        }).orElse(null);
    }

    public void deletePersonne(Long id) {
        personneRepository.deleteById(id);
    }

    public void createPersonnesInEquipe(Long equipeId, List<PersonneDTO> personneDTOs) {
        logger.info("Fetching equipe with ID: " + equipeId);
        Equipe equipe = equipeRepository.findById(equipeId).orElseThrow(() -> new RuntimeException("Equipe not found"));
        logger.info("Equipe found: " + equipe);

        for (PersonneDTO dto : personneDTOs) {
            logger.info("Creating personne with details: " + dto);
            Personne personne = new Personne();
            personne.setNom(dto.getNom());
            personne.setTypeContrat(dto.getTypeContrat());
            personne.setEquipe(equipe);
            equipe.ajouterPersonne(personne);
            personneRepository.save(personne);
            logger.info("Personne created: " + personne);
        }
        equipeRepository.save(equipe);
        logger.info("Equipe updated: " + equipe);
    }
}