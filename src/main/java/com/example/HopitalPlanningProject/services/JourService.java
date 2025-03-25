package com.example.HopitalPlanningProject.services;

import com.example.HopitalPlanningProject.model.Jour;
import com.example.HopitalPlanningProject.repositories.JourRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JourService {
    private final JourRepository jourRepository;

    public JourService(JourRepository jourRepository) {
        this.jourRepository = jourRepository;
    }

    public List<Jour> getAllJours() {
        return jourRepository.findAll();
    }

    public Optional<Jour> getJourById(int id) {
        return jourRepository.findById(id);
    }

    public Jour saveJour(Jour jour) {
        return jourRepository.save(jour);
    }

    public void deleteJour(int id) {
        jourRepository.deleteById(id);
    }
}
