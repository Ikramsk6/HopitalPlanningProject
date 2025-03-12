package com.example.HopitalPlanningProject.controller;

import com.example.HopitalPlanningProject.model.Jour;
import com.example.HopitalPlanningProject.service.JourService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/jours")
public class JourController {

    private final JourService jourService;

    public JourController(JourService jourService) {
        this.jourService = jourService;
    }

    @GetMapping
    public List<Jour> getAllJours() {
        return jourService.getAllJours();
    }

    @GetMapping("/{id}")
    public Optional<Jour> getJourById(@PathVariable String id) {
        return jourService.getJourById(id);
    }

    @PostMapping
    public Jour createJour(@RequestBody Jour jour) {
        return jourService.createJour(jour);
    }

    @DeleteMapping("/{id}")
    public void deleteJour(@PathVariable String id) {
        jourService.deleteJour(id);
    }
}
