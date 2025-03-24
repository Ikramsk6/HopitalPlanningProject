package com.example.HopitalPlanningProject.controllers;

import com.example.HopitalPlanningProject.model.Planning;
import com.example.HopitalPlanningProject.repositories.PlanningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plannings")
public class PlanningController {

    @Autowired
    private PlanningRepository planningRepository;

    @PostMapping
    public Planning createPlanning(@RequestBody Planning planning) {
        return planningRepository.save(planning);
    }

    @GetMapping
    public List<Planning> getAllPlannings() {
        return planningRepository.findAll();
    }

    @GetMapping("/{id}")
    public Planning getPlanningById(@PathVariable int id) {
        return planningRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Planning updatePlanning(@PathVariable int id, @RequestBody Planning planning) {
        planning.setIdPlanning(id);
        return planningRepository.save(planning);
    }

    @DeleteMapping("/{id}")
    public void deletePlanning(@PathVariable int id) {
        planningRepository.deleteById(id);
    }
}
