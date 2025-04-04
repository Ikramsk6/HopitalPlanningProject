package com.example.HopitalPlanningProject.controllers;

import com.example.HopitalPlanningProject.model.Solution;
import com.example.HopitalPlanningProject.services.SolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/solutions")
public class SolutionController {

    @Autowired
    private SolutionService solutionService;

    @GetMapping
    public List<Solution> getAllSolutions() {
        return solutionService.getAllSolutions();
    }

    @PostMapping
    public Solution createSolution(@RequestBody Solution solution) {
        return solutionService.createSolution(solution);
    }
}
