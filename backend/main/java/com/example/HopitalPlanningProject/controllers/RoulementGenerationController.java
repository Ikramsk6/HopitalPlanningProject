package com.example.HopitalPlanningProject.controllers;

import com.example.HopitalPlanningProject.model.Roulement;
import com.example.HopitalPlanningProject.services.RoulementGeneratorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roulements/generate")
public class RoulementGenerationController {

    private final RoulementGeneratorService roulementGeneratorService;

    public RoulementGenerationController(RoulementGeneratorService roulementGeneratorService) {
        this.roulementGeneratorService = roulementGeneratorService;
    }

    @PostMapping
    public Roulement generateRoulement() {
        return roulementGeneratorService.generateRoulement();
    }

    // Modification du second POST pour retourner une liste de roulements générés
    @PostMapping("/{id}")
    public List<Roulement> generateMultipleRoulements(@PathVariable int id) {
        // Cette méthode affiche les résultats dans la console
        return roulementGeneratorService.generateMultipleRoulements(id);
    }
}
