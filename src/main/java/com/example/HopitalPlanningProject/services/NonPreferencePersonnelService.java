package com.example.HopitalPlanningProject.services;

import com.example.HopitalPlanningProject.model.NonPreferencePersonnel;
import com.example.HopitalPlanningProject.model.NonPreferencePersonnelId;
import com.example.HopitalPlanningProject.repositories.NonPreferencePersonnelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NonPreferencePersonnelService {

    private final NonPreferencePersonnelRepository nonPreferencePersonnelRepository;

    public NonPreferencePersonnelService(NonPreferencePersonnelRepository nonPreferencePersonnelRepository) {
        this.nonPreferencePersonnelRepository = nonPreferencePersonnelRepository;
    }

    public List<NonPreferencePersonnel> getAllNonPreferencePersonnel() {
        return nonPreferencePersonnelRepository.findAll();
    }

    public Optional<NonPreferencePersonnel> getNonPreferencePersonnelById(NonPreferencePersonnelId id) {
        return nonPreferencePersonnelRepository.findById(id);
    }

    public NonPreferencePersonnel saveNonPreferencePersonnel(NonPreferencePersonnel nonPreferencePersonnel) {
        return nonPreferencePersonnelRepository.save(nonPreferencePersonnel);
    }

    public void deleteNonPreferencePersonnel(NonPreferencePersonnelId id) {
        nonPreferencePersonnelRepository.deleteById(id);
    }
}
