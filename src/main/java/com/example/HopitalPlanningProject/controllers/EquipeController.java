package com.example.HopitalPlanningProject.controllers;

import com.example.HopitalPlanningProject.models.Equipe;
import com.example.HopitalPlanningProject.models.Personne;
import com.example.HopitalPlanningProject.services.EquipeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/equipes")
public class EquipeController {
    private final EquipeService equipeService;

    public EquipeController(EquipeService equipeService) {
        this.equipeService = equipeService;
    }

    @GetMapping
    public List<Equipe> getAllEquipes() {
        return equipeService.getAllEquipes();
    }

    @GetMapping("/{id}")
    public Optional<Equipe> getEquipeById(@PathVariable Long id) {
        return equipeService.getEquipeById(id);
    }

    @PostMapping
    public Equipe createEquipe(@RequestBody Equipe equipe) {
        return equipeService.createEquipe(equipe);
    }

    @PutMapping("/{id}")
    public Equipe updateEquipe(@PathVariable Long id, @RequestBody Equipe equipeDetails) {
        return equipeService.updateEquipe(id, equipeDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteEquipe(@PathVariable Long id) {
        equipeService.deleteEquipe(id);
    }

    @PostMapping("/{id}/ajouterPersonne")
    public void ajouterPersonne(@PathVariable Long id, @RequestBody Personne personne) {
        equipeService.ajouterPersonne(id, personne);
    }

    @DeleteMapping("/{id}/supprimerPersonne")
    public void supprimerPersonne(@PathVariable Long id, @RequestBody Personne personne) {
        equipeService.supprimerPersonne(id, personne);
    }
}
