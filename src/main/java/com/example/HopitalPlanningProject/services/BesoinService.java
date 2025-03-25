package com.example.HopitalPlanningProject.services;

import com.example.HopitalPlanningProject.model.Besoin;
import com.example.HopitalPlanningProject.model.BesoinId;
import com.example.HopitalPlanningProject.repositories.BesoinRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service pour gérer les besoins.
 */
@Service
public class BesoinService {

    private final BesoinRepository besoinRepository;

    public BesoinService(BesoinRepository besoinRepository) {
        this.besoinRepository = besoinRepository;
    }

    public List<Besoin> getAllBesoins() {
        return besoinRepository.findAll();
    }

    public Optional<Besoin> getBesoinById(BesoinId id) {
        return besoinRepository.findById(id);
    }

    public Besoin saveBesoin(Besoin besoin) {
        return besoinRepository.save(besoin);
    }

    public void deleteBesoin(BesoinId id) {
        besoinRepository.deleteById(id);
    }
}
