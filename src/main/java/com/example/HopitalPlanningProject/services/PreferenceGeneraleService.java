package com.example.HopitalPlanningProject.services;

import com.example.HopitalPlanningProject.model.PreferenceGenerale;
import com.example.HopitalPlanningProject.repositories.PreferenceGeneraleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PreferenceGeneraleService {

    @Autowired
    private PreferenceGeneraleRepository preferenceGeneraleRepository;

    public List<PreferenceGenerale> getAllPreferences() {
        return preferenceGeneraleRepository.findAll();
    }

    public Optional<PreferenceGenerale> getPreferenceById(int id) {
        return preferenceGeneraleRepository.findById(id);
    }

    public PreferenceGenerale createPreference(PreferenceGenerale preferenceGenerale) {
        return preferenceGeneraleRepository.save(preferenceGenerale);
    }

    public void deletePreference(int id) {
        preferenceGeneraleRepository.deleteById(id);
    }
}

