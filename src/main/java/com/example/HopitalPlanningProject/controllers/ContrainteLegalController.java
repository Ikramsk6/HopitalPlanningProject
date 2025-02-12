package com.example.HopitalPlanningProject.controllers;

import com.example.HopitalPlanningProject.models.ContrainteLegal;
import com.example.HopitalPlanningProject.services.ContrainteLegalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contraintesLegal")
public class ContrainteLegalController {
    private final ContrainteLegalService contrainteLegalService;

    public ContrainteLegalController(ContrainteLegalService contrainteLegalService) {
        this.contrainteLegalService = contrainteLegalService;
    }

    @GetMapping
    public List<ContrainteLegal> getAllContraintesLegal() {
        return contrainteLegalService.getAllContraintesLegal();
    }

    @GetMapping("/{id}")
    public Optional<ContrainteLegal> getContrainteLegalById(@PathVariable Long id) {
        return contrainteLegalService.getContrainteLegalById(id);
    }

    @PostMapping
    public ContrainteLegal saveContrainteLegal(@RequestBody ContrainteLegal contrainteLegal) {
        return contrainteLegalService.saveContrainteLegal(contrainteLegal);
    }

    @DeleteMapping("/{id}")
    public void deleteContrainteLegal(@PathVariable Long id) {
        contrainteLegalService.deleteContrainteLegal(id);
    }
}
