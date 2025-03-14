package com.example.HopitalPlanningProject.services;

import com.example.HopitalPlanningProject.model.Solution;
import com.example.HopitalPlanningProject.repositories.SolutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SolutionService {

    @Autowired
    private SolutionRepository solutionRepository;

    public List<Solution> getAllSolutions() {
        return solutionRepository.findAll();
    }

    public Optional<Solution> getSolutionById(int id) {
        return solutionRepository.findById(id);
    }

    public Solution createSolution(Solution solution) {
        return solutionRepository.save(solution);
    }

    public void deleteSolution(int id) {
        solutionRepository.deleteById(id);
    }
}

