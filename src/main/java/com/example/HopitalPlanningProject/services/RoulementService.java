package com.example.HopitalPlanningProject.services;

import com.example.HopitalPlanningProject.model.Roulement;
import com.example.HopitalPlanningProject.repositories.RoulementRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service pour gérer les roulements.
 */
@Service
public class RoulementService {
    private final RoulementRepository roulementRepository;

    public RoulementService(RoulementRepository roulementRepository) {
        this.roulementRepository = roulementRepository;
    }

    public List<Roulement> getAllRoulements() {
        return roulementRepository.findAll();
    }

    public Optional<Roulement> getRoulementById(int id) {
        return roulementRepository.findById(id);
    }

    public Roulement saveRoulement(Roulement roulement) {
        return roulementRepository.save(roulement);
    }

    public void deleteRoulement(int id) {
        roulementRepository.deleteById(id);
    }
}
