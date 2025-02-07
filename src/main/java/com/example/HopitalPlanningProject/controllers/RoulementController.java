package com.example.HopitalPlanningProject.controllers;

import com.example.HopitalPlanningProject.models.Roulement;
import com.example.HopitalPlanningProject.services.RoulementService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/roulements")
public class RoulementController {
    private final RoulementService roulementService;

    public RoulementController(RoulementService roulementService) {
        this.roulementService = roulementService;
    }

    @GetMapping
    public List<Roulement> getAllRoulements() {
        return roulementService.getAllRoulements();
    }

    @GetMapping("/{id}")
    public Optional<Roulement> getRoulementById(@PathVariable Long id) {
        return roulementService.getRoulementById(id);
    }

    @PostMapping
    public Roulement createRoulement(@RequestBody Roulement roulement) {
        return roulementService.createRoulement(roulement);
    }

    @DeleteMapping("/{id}")
    public void deleteRoulement(@PathVariable Long id) {
        roulementService.deleteRoulement(id);
    }
}
