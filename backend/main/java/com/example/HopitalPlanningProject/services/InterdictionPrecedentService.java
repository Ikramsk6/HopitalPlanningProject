package com.example.HopitalPlanningProject.services;

import com.example.HopitalPlanningProject.model.InterdictionPrecedent;
import com.example.HopitalPlanningProject.repositories.InterdictionPrecedentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InterdictionPrecedentService {

    @Autowired
    private InterdictionPrecedentRepository interdictionPrecedentRepository;

    public List<InterdictionPrecedent> getAllInterdictions() {
        return interdictionPrecedentRepository.findAll();
    }

    public Optional<InterdictionPrecedent> getInterdictionById(int id) {
        return interdictionPrecedentRepository.findById(id);
    }

    public InterdictionPrecedent createInterdiction(InterdictionPrecedent interdictionPrecedent) {
        return interdictionPrecedentRepository.save(interdictionPrecedent);
    }

    public void deleteInterdiction(int id) {
        interdictionPrecedentRepository.deleteById(id);
    }
}
