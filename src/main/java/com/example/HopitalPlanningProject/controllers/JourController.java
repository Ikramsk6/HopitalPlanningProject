package com.example.HopitalPlanningProject.controllers;

import com.example.HopitalPlanningProject.model.Jour;
import com.example.HopitalPlanningProject.services.JourService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/jours")
public class JourController {

    private final JourService jourService;

    // Injection du service via le constructeur
    public JourController(JourService jourService) {
        this.jourService = jourService;
    }

    // Récupérer tous les jours
    @GetMapping
    public List<Jour> getAllJours() {
        return jourService.getAllJours();
    }

    // Récupérer un jour par son ID
    @GetMapping("/{id}")
    public Optional<Jour> getJourById(@PathVariable int id) {
        return jourService.getJourById(id);
    }

    // Créer un jour
    @PostMapping
    public Jour createJour(@RequestBody Jour jour) {
        return jourService.saveJour(jour);
    }

    // Supprimer un jour par son ID
    @DeleteMapping("/{id}")
    public void deleteJour(@PathVariable int id) {
        jourService.deleteJour(id);
    }
}
