package com.example.HopitalPlanningProject.services;

import com.example.HopitalPlanningProject.model.Planning;
import com.example.HopitalPlanningProject.repositories.PlanningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanningService {

    @Autowired
    private PlanningRepository planningRepository;

     public Planning createPlanning(Planning planning) {
        return planningRepository.save(planning);
    }

     public List<Planning> getAllPlannings() {
        return planningRepository.findAll();
    }

     public Optional<Planning> getPlanningById(int id) {
        return planningRepository.findById(id);
    }

     public Planning updatePlanning(int id, Planning updatedPlanning) {
        updatedPlanning.setIdPlanning(id);
        return planningRepository.save(updatedPlanning);
    }

     public void deletePlanning(int id) {
        planningRepository.deleteById(id);
    }
}
