package com.example.HopitalPlanningProject.mappers;
import com.example.HopitalPlanningProject.DTO.PreferenceDTO;
import com.example.HopitalPlanningProject.models.Preference;
import com.example.HopitalPlanningProject.models.Shift;

public class PreferenceMapper {

    // Convertir un PreferenceDTO en Preference
    public static Preference toEntity(PreferenceDTO preferenceDTO) {
        if (preferenceDTO == null) {
            return null;
        }

        Preference preference = new Preference();
        preference.setId(preferenceDTO.getId());
        preference.setJour(preferenceDTO.getJour());

        // Récupérer l'entité Shift en fonction de l'ID
        Shift shift = new Shift(); // Remplacer par une récupération de Shift en fonction de preferenceDTO.getShiftId()
        preference.setShift(shift);

        preference.setEcart(preferenceDTO.getEcart());

        return preference;
    }

    // Convertir un Preference en PreferenceDTO
    public static PreferenceDTO toDTO(Preference preference) {
        if (preference == null) {
            return null;
        }

        PreferenceDTO preferenceDTO = new PreferenceDTO();
        preferenceDTO.setId(preference.getId());
        preferenceDTO.setJour(preference.getJour());
        preferenceDTO.setEcart(preference.getEcart());

        // Ajouter l'ID du Shift dans le DTO
        preferenceDTO.setShiftId(preference.getShift().getId());

        return preferenceDTO;
    }
}
