package com.example.HopitalPlanningProject.mappers;
import com.example.HopitalPlanningProject.DTO.EquipeDTO;
import com.example.HopitalPlanningProject.models.Equipe;

public class EquipeMapper {

    // Convertir un EquipeDTO en Equipe
    public static Equipe toEntity(EquipeDTO equipeDTO) {
        if (equipeDTO == null) {
            return null;
        }

        Equipe equipe = new Equipe();
        equipe.setId(equipeDTO.getId());
        equipe.setTailleEquipe(equipeDTO.getTailleEquipe());

        // Pour l'exemple, on ne traite pas la liste de personnes ici (c'est géré différemment dans l'entité)

        return equipe;
    }

    // Convertir un Equipe en EquipeDTO
    public static EquipeDTO toDTO(Equipe equipe) {
        if (equipe == null) {
            return null;
        }

        EquipeDTO equipeDTO = new EquipeDTO();
        equipeDTO.setId(equipe.getId());
        equipeDTO.setTailleEquipe(equipe.getTailleEquipe());

        return equipeDTO;
    }
}
