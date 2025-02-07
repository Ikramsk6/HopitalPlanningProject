package com.example.HopitalPlanningProject.services;

import com.example.HopitalPlanningProject.models.Contrainte;
import com.example.HopitalPlanningProject.repositories.ContrainteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContrainteService {
    private final ContrainteRepository contrainteRepository;

    public ContrainteService(ContrainteRepository contrainteRepository) {
        this.contrainteRepository = contrainteRepository;
    }

    public List<Contrainte> getAllContraintes() {
        return contrainteRepository.findAll();
    }

    public Optional<Contrainte> getContrainteById(Long id) {
        return contrainteRepository.findById(id);
    }

    public Contrainte createContrainte(Contrainte contrainte) {
        return contrainteRepository.save(contrainte);
    }

    public void deleteContrainte(Long id) {
        contrainteRepository.deleteById(id);
    }
}
