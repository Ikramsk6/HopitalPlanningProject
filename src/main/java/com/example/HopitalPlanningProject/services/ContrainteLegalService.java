package com.example.HopitalPlanningProject.services;

import com.example.HopitalPlanningProject.models.ContrainteLegal;
import com.example.HopitalPlanningProject.repositories.ContrainteLegalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContrainteLegalService {
    private final ContrainteLegalRepository contrainteLegalRepository;

    public ContrainteLegalService(ContrainteLegalRepository contrainteLegalRepository) {
        this.contrainteLegalRepository = contrainteLegalRepository;
    }

    public List<ContrainteLegal> getAllContraintesLegal() {
        return contrainteLegalRepository.findAll();
    }

    public Optional<ContrainteLegal> getContrainteLegalById(Long id) {
        return contrainteLegalRepository.findById(id);
    }

    public ContrainteLegal saveContrainteLegal(ContrainteLegal contrainteLegal) {
        return contrainteLegalRepository.save(contrainteLegal);
    }

    public void deleteContrainteLegal(Long id) {
        contrainteLegalRepository.deleteById(id);
    }
}
