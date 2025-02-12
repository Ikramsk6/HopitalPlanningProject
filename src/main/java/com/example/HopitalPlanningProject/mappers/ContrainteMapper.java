package com.example.HopitalPlanningProject.mappers;

import com.example.HopitalPlanningProject.DTO.ContrainteDTO;
import com.example.HopitalPlanningProject.models.Contrainte;
import com.example.HopitalPlanningProject.models.ContrainteLegal;

public class ContrainteMapper {

    public static ContrainteDTO toDTO(Contrainte contrainte) {
        if (contrainte == null) {
            return null;
        }
        ContrainteDTO dto = new ContrainteDTO();
        dto.setId(contrainte.getId());
        dto.setDtype(contrainte.getClass().getSimpleName());  // Ajoute le type de la contrainte
        dto.setType(contrainte.getClass().getSimpleName());    // Exemple : "ContrainteLegal"
        dto.setDescription("");  // Description peut être commune ou spécifique à chaque contrainte
        dto.setDetails("");  // Idem pour les détails
        return dto;
    }

    public static Contrainte toEntity(ContrainteDTO contrainteDTO) {
        if (contrainteDTO == null) {
            return null;
        }

        // Utilisation de la réflexion pour instancier la sous-classe appropriée
        try {
            Class<?> clazz = Class.forName("com.example.HopitalPlanningProject.models." + contrainteDTO.getDtype());
            Contrainte contrainte = (Contrainte) clazz.getDeclaredConstructor().newInstance();

            contrainte.setId(contrainteDTO.getId());
            if (contrainte instanceof ContrainteLegal) {
                ContrainteLegal contrainteLegal = (ContrainteLegal) contrainte;
                contrainteLegal.setType(contrainteDTO.getType());
                contrainteLegal.setDescription(contrainteDTO.getDescription());
            }
            // Ajouter ici d'autres cas si tu as d'autres sous-classes, par exemple:
            // else if (contrainte instanceof ContrainteHoraire) { ... }

            return contrainte;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
