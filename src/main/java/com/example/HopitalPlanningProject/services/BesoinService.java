package com.example.HopitalPlanningProject.services;


import com.example.HopitalPlanningProject.models.Besoin;
import com.example.HopitalPlanningProject.repositories.BesoinRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BesoinService {
    private final BesoinRepository besoinRepository;

    public BesoinService(BesoinRepository besoinRepository) {
        this.besoinRepository = besoinRepository;
    }

    public List<Besoin> getAllBesoins() {
        return besoinRepository.findAll();
    }

    public Optional<Besoin> getBesoinById(Long id) {
        return besoinRepository.findById(id);
    }

    public Besoin createBesoin(Besoin besoin) {
        return besoinRepository.save(besoin);
    }

    public void deleteBesoin(Long id) {
        besoinRepository.deleteById(id);
    }
}

