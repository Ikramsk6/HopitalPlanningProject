package com.example.HopitalPlanningProject.controllers;

import com.example.HopitalPlanningProject.models.Planning;
import com.example.HopitalPlanningProject.services.PlanningService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/plannings")
public class PlanningController {
    private final PlanningService planningService;

    public PlanningController(PlanningService planningService) {
        this.planningService = planningService;
    }

    @GetMapping
    public List<Planning> getAllPlannings() {
        return planningService.getAllPlannings();
    }

    @GetMapping("/{id}")
    public Optional<Planning> getPlanningById(@PathVariable Long id) {
        return planningService.getPlanningById(id);
    }

    @PostMapping
    public Planning savePlanning(@RequestBody Planning planning) {
        return planningService.savePlanning(planning);
    }

    @DeleteMapping("/{id}")
    public void deletePlanning(@PathVariable Long id) {
        planningService.deletePlanning(id);
    }

    @PostMapping("/{planningId}/generer")
    public void genererPlanning(@PathVariable Long planningId) {
        planningService.genererPlanning(planningId);
    }
}
