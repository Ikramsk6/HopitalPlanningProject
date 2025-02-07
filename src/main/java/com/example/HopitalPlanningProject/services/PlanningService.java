package com.example.HopitalPlanningProject.services;

import com.example.HopitalPlanningProject.models.Planning;
import com.example.HopitalPlanningProject.repositories.PlanningRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanningService {
    private final PlanningRepository planningRepository;

    public PlanningService(PlanningRepository planningRepository) {
        this.planningRepository = planningRepository;
    }

    public List<Planning> getAllPlannings() {
        return planningRepository.findAll();
    }

    public Optional<Planning> getPlanningById(Long id) {
        return planningRepository.findById(id);
    }

    public Planning createPlanning(Planning planning) {
        return planningRepository.save(planning);
    }

    public void deletePlanning(Long id) {
        planningRepository.deleteById(id);
    }
}
