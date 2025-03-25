package com.example.HopitalPlanningProject.services;

import com.example.HopitalPlanningProject.model.PreferencePersonnel;
import com.example.HopitalPlanningProject.model.PreferencePersonnelId;
import com.example.HopitalPlanningProject.repositories.PreferencePersonnelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PreferencePersonnelService {

    private final PreferencePersonnelRepository preferencePersonnelRepository;

    public PreferencePersonnelService(PreferencePersonnelRepository preferencePersonnelRepository) {
        this.preferencePersonnelRepository = preferencePersonnelRepository;
    }

    // Méthode pour obtenir toutes les préférences de personnel
    public List<PreferencePersonnel> getAllPreferencePersonnel() {
        return preferencePersonnelRepository.findAll();
    }

    // Méthode pour obtenir une préférence de personnel par son ID
    public Optional<PreferencePersonnel> getPreferencePersonnelById(PreferencePersonnelId id) {
        return preferencePersonnelRepository.findById(id);
    }

    // Méthode pour créer ou mettre à jour une préférence de personnel
    public PreferencePersonnel savePreferencePersonnel(PreferencePersonnel preferencePersonnel) {
        return preferencePersonnelRepository.save(preferencePersonnel);
    }

    // Méthode pour supprimer une préférence de personnel
    public void deletePreferencePersonnel(PreferencePersonnelId id) {
        preferencePersonnelRepository.deleteById(id);
    }
}
