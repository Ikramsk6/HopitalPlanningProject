package com.example.HopitalPlanningProject.services;

import com.example.HopitalPlanningProject.model.Equipe;
import com.example.HopitalPlanningProject.repositories.EquipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipeService {

    @Autowired
    private EquipeRepository equipeRepository;

    public List<Equipe> getAllEquipes() {
        return equipeRepository.findAll();
    }

    public Optional<Equipe> getEquipeById(int id) {
        return equipeRepository.findById(id);
    }

    public Equipe saveEquipe(Equipe equipe) {
        return equipeRepository.save(equipe);
    }

    public Equipe updateEquipe(int id, Equipe equipe) {
        Optional<Equipe> existingEquipe = equipeRepository.findById(id);
        if (existingEquipe.isPresent()) {
            equipe.setIdEquipe(id);
            return equipeRepository.save(equipe);
        } else {
            return null;
        }
    }

    public void deleteEquipe(int id) {
        equipeRepository.deleteById(id);
    }
}
