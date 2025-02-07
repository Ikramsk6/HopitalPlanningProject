package com.example.HopitalPlanningProject.services;

import com.example.HopitalPlanningProject.models.Personne;
import com.example.HopitalPlanningProject.repositories.PersonneRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonneService {
    private final PersonneRepository personneRepository;

    public PersonneService(PersonneRepository personneRepository) {
        this.personneRepository = personneRepository;
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
}
