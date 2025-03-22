package com.example.HopitalPlanningProject.services;

import com.example.HopitalPlanningProject.model.PreferencePersonnel;
import com.example.HopitalPlanningProject.repositories.PreferencePersonnelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PreferencePersonnelService {

    @Autowired
    private PreferencePersonnelRepository repository;

    public List<PreferencePersonnel> getAll() {
        return repository.findAll();
    }

    public PreferencePersonnel create(PreferencePersonnel preference) {
        return repository.save(preference);
    }
}
