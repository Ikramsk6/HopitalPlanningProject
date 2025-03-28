package com.example.HopitalPlanningProject.controllers;

import com.example.HopitalPlanningProject.model.Contrat;
import com.example.HopitalPlanningProject.services.ContratService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3001")
@RestController
@RequestMapping("/api/contrats")
public class ContratController {

    private final ContratService contratService;

    public ContratController(ContratService contratService) {
        this.contratService = contratService;
    }

    @GetMapping
    public List<Contrat> getAllContrats() {
        return contratService.getAllContrats();
    }

    @GetMapping("/{id}")
    public Optional<Contrat> getContratById(@PathVariable int id) {
        return contratService.getContratById(id);
    }

    @PostMapping
    public Contrat createContrat(@RequestBody Contrat contrat) {
        if (contrat.getDescriptionContrat() == null || contrat.getPourcentageTravail() <= 0) {
            throw new IllegalArgumentException("Description ou pourcentage de travail manquants");
        }
        return contratService.saveContrat(contrat);
    }

    @PutMapping("/{id}")
    public Contrat updateContrat(@PathVariable int id, @RequestBody Contrat contrat) {
        return contratService.updateContrat(id, contrat); // Si tu implémente cette méthode dans le service
    }

    @DeleteMapping("/{id}")
    public void deleteContrat(@PathVariable int id) {
        contratService.deleteContrat(id);
    }
}
