package com.example.HopitalPlanningProject.controllers;

import com.example.HopitalPlanningProject.model.Equipe;
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
    public Optional<Equipe> getEquipeById(@PathVariable int id) {
        return equipeService.getEquipeById(id);
    }

    // @PostMapping
    // public Equipe createEquipe(@RequestBody Equipe equipe) {
    //     return equipeService.createEquipe(equipe);
    // }

    // @PutMapping("/{id}")
    // public Equipe updateEquipe(@PathVariable int id, @RequestBody Equipe equipe) {
    //     return equipeService.updateEquipe(id, equipe);
    // }

    @DeleteMapping("/{id}")
    public void deleteEquipe(@PathVariable int id) {
        equipeService.deleteEquipe(id);
    }
}
