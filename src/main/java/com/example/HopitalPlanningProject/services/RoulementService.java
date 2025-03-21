package com.example.HopitalPlanningProject.services;

import com.example.HopitalPlanningProject.models.Roulement;
import com.example.HopitalPlanningProject.models.Shift;
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

    public List<Roulement> getAllRoulements() {
        return roulementRepository.findAll();
    }

    public Optional<Roulement> getRoulementById(Long id) {
        return roulementRepository.findById(id);
    }

    public Roulement saveRoulement(Roulement roulement) {
        return roulementRepository.save(roulement);
    }

    public void deleteRoulement(Long id) {
        roulementRepository.deleteById(id);
    }

    public void ajouterShiftAuRoulement(Long roulementId, Shift shift) {
        Optional<Roulement> roulementOpt = roulementRepository.findById(roulementId);
        roulementOpt.ifPresent(roulement -> {
            roulement.ajouterShift(shift);
            roulementRepository.save(roulement);
        });
    }
}
