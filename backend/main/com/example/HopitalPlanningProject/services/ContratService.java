package com.example.HopitalPlanningProject.services;

import com.example.HopitalPlanningProject.erreurs.ContratNotFoundException;
import com.example.HopitalPlanningProject.model.Contrat;
import com.example.HopitalPlanningProject.repositories.ContratRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContratService {
    private final ContratRepository contratRepository;

    public ContratService(ContratRepository contratRepository) {
        this.contratRepository = contratRepository;
    }

    public List<Contrat> getAllContrats() {
        return contratRepository.findAll();
    }

    public Contrat getContratById(int id) {
        return contratRepository.findById(id)
                .orElseThrow(() -> new ContratNotFoundException("Contrat not found with id: " + id));
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

    /**
     * Initialise les contrats par défaut (100% et 50%) s'ils n'existent pas déjà dans la base de données.
     */
    public void initializeDefaultContrats() {
        // Vérifier si les contrats de base existent déjà
        if (!contratRepository.existsByDescriptionContrat("100%")) {
            Contrat contrat100 = new Contrat();
            contrat100.setDescriptionContrat("100%");
            contrat100.setPourcentageTravail(100.00);
            contratRepository.save(contrat100);
        }

        if (!contratRepository.existsByDescriptionContrat("50%")) {
            Contrat contrat50 = new Contrat();
            contrat50.setDescriptionContrat("50%");
            contrat50.setPourcentageTravail(50.00);
            contratRepository.save(contrat50);
        }
    }
    public Contrat createOrGetContrat(Contrat contrat) {
        if (contrat.getIdContrat() != 0) {
            // Si l'ID est valide, on tente de récupérer le contrat existant
            return contratRepository.findById(contrat.getIdContrat())
                    .orElseThrow(() -> new IllegalArgumentException("Contrat non trouvé"));
        } else {
            // Sinon, on crée un nouveau contrat
            return contratRepository.save(contrat);
        }
    }
}
