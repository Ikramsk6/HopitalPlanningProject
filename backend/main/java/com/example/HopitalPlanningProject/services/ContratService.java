package com.example.HopitalPlanningProject.services;

import com.example.HopitalPlanningProject.model.Contrat;
import com.example.HopitalPlanningProject.repositories.ContratRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        return contratRepository.save(contrat); // Sauvegarde le contrat dans la base de données
    }

    public Contrat updateContrat(int id, Contrat contrat) {
        // Logique de mise à jour si nécessaire, sinon implémente une mise à jour si nécessaire
        contrat.setIdContrat(id); // Si l'ID est modifiable
        return contratRepository.save(contrat); // Sauvegarde le contrat mis à jour
    }

    public void deleteContrat(int id) {
        contratRepository.deleteById(id);
    }
}
