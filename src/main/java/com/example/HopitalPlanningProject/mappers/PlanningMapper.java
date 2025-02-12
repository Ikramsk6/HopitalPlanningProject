package com.example.HopitalPlanningProject.mappers;
import com.example.HopitalPlanningProject.DTO.PlanningDTO;
import com.example.HopitalPlanningProject.models.Planning;
import com.example.HopitalPlanningProject.models.Roulement;
import com.example.HopitalPlanningProject.models.Besoin;
import com.example.HopitalPlanningProject.models.Preference;

import java.util.List;

public class PlanningMapper {

    // Convertir un PlanningDTO en Planning
    public static Planning toEntity(PlanningDTO planningDTO) {
        if (planningDTO == null) {
            return null;
        }

        Planning planning = new Planning();
        planning.setId(planningDTO.getId());
        planning.setNbMaxRoulement(planningDTO.getNbMaxRoulement());
        planning.setTailleRoulement(planningDTO.getTailleRoulement());

        // Convertir les IDs en objets Roulement, Besoin, Preference
        List<Long> roulementIds = planningDTO.getRoulementIds();
        // Ici, ajouter le code pour récupérer les entités Roulement en fonction des IDs

        List<Long> besoinIds = planningDTO.getBesoinIds();
        // Ici, ajouter le code pour récupérer les entités Besoin en fonction des IDs

        List<Long> preferenceIds = planningDTO.getPreferenceIds();
        // Ici, ajouter le code pour récupérer les entités Preference en fonction des IDs

        // Assurez-vous que les entités sont récupérées correctement dans la base de données
        // Exemple d'ajout des entités à la liste:
        for (Long id : roulementIds) {
            Roulement roulement = new Roulement(); // Remplacer par une récupération depuis la base
            planning.ajouterRoulement(roulement);
        }

        for (Long id : besoinIds) {
            Besoin besoin = new Besoin(); // Remplacer par une récupération depuis la base
            planning.ajouterBesoin(besoin);
        }

        for (Long id : preferenceIds) {
            Preference preference = new Preference(); // Remplacer par une récupération depuis la base
            planning.getPreferences().add(preference);
        }

        return planning;
    }

    // Convertir un Planning en PlanningDTO
    public static PlanningDTO toDTO(Planning planning) {
        if (planning == null) {
            return null;
        }

        PlanningDTO planningDTO = new PlanningDTO();
        planningDTO.setId(planning.getId());
        planningDTO.setNbMaxRoulement(planning.getNbMaxRoulement());
        planningDTO.setTailleRoulement(planning.getTailleRoulement());

        // Récupérer les IDs des entités associées
        List<Long> roulementIds = planning.getRoulements().stream()
                .map(Roulement::getId)
                .toList();
        planningDTO.setRoulementIds(roulementIds);

        List<Long> besoinIds = planning.getBesoins().stream()
                .map(Besoin::getId)
                .toList();
        planningDTO.setBesoinIds(besoinIds);

        List<Long> preferenceIds = planning.getPreferences().stream()
                .map(Preference::getId)
                .toList();
        planningDTO.setPreferenceIds(preferenceIds);

        return planningDTO;
    }
}
