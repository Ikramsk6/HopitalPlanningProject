package com.example.HopitalPlanningProject.services;

import com.example.HopitalPlanningProject.model.Equipe;
import com.example.HopitalPlanningProject.repositories.EquipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service pour gérer les équipes.
 */
@Service
public class EquipeService {
    private final EquipeRepository equipeRepository;

    public EquipeService(EquipeRepository equipeRepository) {
        this.equipeRepository = equipeRepository;
    }

    public List<Equipe> getAllEquipes() {
        return equipeRepository.findAll();
    }

    public Optional<Equipe> getEquipeById(int id) {
        return equipeRepository.findById(id);
    }

    public Equipe saveEquipe(Equipe equipe) {
        return equipeRepository.save(equipe);
    }

    public void deleteEquipe(int id) {
        equipeRepository.deleteById(id);
    }
}
