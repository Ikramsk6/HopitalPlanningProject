package com.example.HopitalPlanningProject.services;

import com.example.HopitalPlanningProject.model.Contrat;
import com.example.HopitalPlanningProject.repositories.ContratRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service pour gérer les contrats.
 */
@Service
public class ContratService {
    private final ContratRepository contratRepository;

    public ContratService(ContratRepository contratRepository) {
        this.contratRepository = contratRepository;
    }

    public List<Contrat> getAllContrats() {
        return contratRepository.findAll();
    }

    public Optional<Contrat> getContratById(int id) {
        return contratRepository.findById(id);
    }

    public Contrat saveContrat(Contrat contrat) {
        return contratRepository.save(contrat);
    }

    public void deleteContrat(int id) {
        contratRepository.deleteById(id);
    }
}
