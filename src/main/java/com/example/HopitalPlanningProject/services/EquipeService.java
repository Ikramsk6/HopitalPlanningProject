package com.example.HopitalPlanningProject.services;

import com.example.HopitalPlanningProject.models.Equipe;
import com.example.HopitalPlanningProject.models.Personne;
import com.example.HopitalPlanningProject.repositories.EquipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipeService {
    private final EquipeRepository equipeRepository;

    public EquipeService(EquipeRepository equipeRepository) {
        this.equipeRepository = equipeRepository;
    }

    public List<Equipe> getAllEquipes() {
        return equipeRepository.findAll();
    }

    public Optional<Equipe> getEquipeById(Long id) {
        return equipeRepository.findById(id);
    }

    public Equipe createEquipe(Equipe equipe) {
        return equipeRepository.save(equipe);
    }

    public Equipe updateEquipe(Long id, Equipe equipeDetails) {
        return equipeRepository.findById(id).map(equipe -> {
            equipe.setTailleEquipe(equipeDetails.getTailleEquipe());
            return equipeRepository.save(equipe);
        }).orElse(null);
    }

    public void deleteEquipe(Long id) {
        equipeRepository.deleteById(id);
    }

    public void ajouterPersonne(Long equipeId, Personne personne) {
        equipeRepository.findById(equipeId).ifPresent(equipe -> {
            equipe.ajouterPersonne(personne);
            equipeRepository.save(equipe);
        });
    }

    public void supprimerPersonne(Long equipeId, Personne personne) {
        equipeRepository.findById(equipeId).ifPresent(equipe -> {
            equipe.supprimerPersonne(personne);
            equipeRepository.save(equipe);
        });
    }
}
