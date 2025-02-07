package com.example.HopitalPlanningProject.controllers;

import com.example.HopitalPlanningProject.models.Contrainte;
import com.example.HopitalPlanningProject.services.ContrainteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contraintes")
public class ContrainteController {
    private final ContrainteService contrainteService;

    public ContrainteController(ContrainteService contrainteService) {
        this.contrainteService = contrainteService;
    }

    @GetMapping
    public List<Contrainte> getAllContraintes() {
        return contrainteService.getAllContraintes();
    }

    @GetMapping("/{id}")
    public Optional<Contrainte> getContrainteById(@PathVariable Long id) {
        return contrainteService.getContrainteById(id);
    }

    @PostMapping
    public Contrainte createContrainte(@RequestBody Contrainte contrainte) {
        return contrainteService.createContrainte(contrainte);
    }

    @DeleteMapping("/{id}")
    public void deleteContrainte(@PathVariable Long id) {
        contrainteService.deleteContrainte(id);
    }
}
