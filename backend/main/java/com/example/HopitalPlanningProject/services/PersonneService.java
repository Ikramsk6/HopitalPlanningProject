package com.example.HopitalPlanningProject.services;

import com.example.HopitalPlanningProject.model.Personne;
import com.example.HopitalPlanningProject.repositories.PersonneRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service pour gérer les personnes.
 */
@Service
public class PersonneService {
    private final PersonneRepository personneRepository;

    public PersonneService(PersonneRepository personneRepository) {
        this.personneRepository = personneRepository;
    }

    public List<Personne> getAllPersonnes() {
        return personneRepository.findAll();
    }

    public Optional<Personne> getPersonneById(int id) {
        return personneRepository.findById(id);
    }

    public Personne savePersonne(Personne personne) {
        return personneRepository.save(personne);
    }

    public void deletePersonne(int id) {
        personneRepository.deleteById(id);
    }
}
