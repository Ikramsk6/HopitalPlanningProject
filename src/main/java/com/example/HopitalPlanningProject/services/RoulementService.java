package com.example.HopitalPlanningProject.services;

import com.example.HopitalPlanningProject.model.Roulement;
import com.example.HopitalPlanningProject.repositories.RoulementRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoulementService {

    private final RoulementRepository roulementRepository;

    public RoulementService(RoulementRepository roulementRepository) {
        this.roulementRepository = roulementRepository;
    }

    // Méthode pour récupérer tous les roulements
    public List<Roulement> getAllRoulements() {
        return roulementRepository.findAll();
    }

    // Méthode pour récupérer un roulement par son ID
    public Optional<Roulement> getRoulementById(int id) {
        return roulementRepository.findById(id);
    }

    // Méthode pour enregistrer ou mettre à jour un roulement
    public Roulement saveRoulement(Roulement roulement) {
        return roulementRepository.save(roulement);
    }

    // Méthode pour supprimer un roulement
    public void deleteRoulement(int id) {
        roulementRepository.deleteById(id);
    }
}
