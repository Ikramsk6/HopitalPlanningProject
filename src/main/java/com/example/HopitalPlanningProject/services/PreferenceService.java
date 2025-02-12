package com.example.HopitalPlanningProject.services;

import com.example.HopitalPlanningProject.models.Preference;
import com.example.HopitalPlanningProject.models.Shift;
import com.example.HopitalPlanningProject.repositories.PreferenceRepository;
import com.example.HopitalPlanningProject.repositories.ShiftRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PreferenceService {
    private final PreferenceRepository preferenceRepository;
    private final ShiftRepository shiftRepository;

    public PreferenceService(PreferenceRepository preferenceRepository, ShiftRepository shiftRepository) {
        this.preferenceRepository = preferenceRepository;
        this.shiftRepository = shiftRepository;
    }

    public List<Preference> getAllPreferences() {
        return preferenceRepository.findAll();
    }

    public Optional<Preference> getPreferenceById(Long id) {
        return preferenceRepository.findById(id);
    }

    public Preference createPreference(Long shiftId, String jour, int ecart) {
        Optional<Shift> shiftOpt = shiftRepository.findById(shiftId);

        if (shiftOpt.isPresent()) {
            Preference preference = new Preference();
            preference.setShift(shiftOpt.get());
            preference.setJour(jour);
            preference.setEcart(ecart);
            return preferenceRepository.save(preference);
        }
        return null;
    }

    public Preference updatePreference(Long id, String jour, int ecart) {
        return preferenceRepository.findById(id).map(preference -> {
            preference.setJour(jour);
            preference.setEcart(ecart);
            return preferenceRepository.save(preference);
        }).orElse(null);
    }

    public void deletePreference(Long id) {
        preferenceRepository.deleteById(id);
    }
}
