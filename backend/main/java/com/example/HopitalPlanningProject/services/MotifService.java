package com.example.HopitalPlanningProject.services;

import com.example.HopitalPlanningProject.model.Motif;
import com.example.HopitalPlanningProject.repositories.MotifRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MotifService {

    @Autowired
    private MotifRepository motifRepository;

    public List<Motif> getAllMotifs() {
        return motifRepository.findAll();
    }

    public Optional<Motif> getMotifById(String id) {
        return motifRepository.findById(id);
    }

    public Motif createMotif(Motif motif) {
        return motifRepository.save(motif);
    }

    public void deleteMotif(String id) {
        motifRepository.deleteById(id);
    }
}
