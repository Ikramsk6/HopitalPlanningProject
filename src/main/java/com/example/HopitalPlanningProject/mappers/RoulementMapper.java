package com.example.HopitalPlanningProject.mappers;
import com.example.HopitalPlanningProject.DTO.RoulementDTO;
import com.example.HopitalPlanningProject.models.Roulement;
import com.example.HopitalPlanningProject.models.Shift;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RoulementMapper {

    // Convertir un RoulementDTO en Roulement
    public static Roulement toEntity(RoulementDTO roulementDTO) {
        if (roulementDTO == null) {
            return null;
        }

        Roulement roulement = new Roulement();
        roulement.setId(roulementDTO.getId());
        roulement.setDureeRoulement(roulementDTO.getDureeRoulement());
        roulement.setContraintes(roulementDTO.getContraintes());

        // Récupérer la liste des entités Shift à partir des ID
        List<Shift> shifts = new ArrayList<>(); // Remplacer cette partie par une récupération des objets Shift via leurs IDs
        roulement.setShifts(shifts);

        return roulement;
    }

    // Convertir un Roulement en RoulementDTO
    public static RoulementDTO toDTO(Roulement roulement) {
        if (roulement == null) {
            return null;
        }

        RoulementDTO roulementDTO = new RoulementDTO();
        roulementDTO.setId(roulement.getId());
        roulementDTO.setDureeRoulement(roulement.getDureeRoulement());
        roulementDTO.setContraintes(roulement.getContraintes());

        // Ajouter les IDs des shifts dans le DTO
        List<Long> shiftIds = roulement.getShifts().stream()
                .map(Shift::getId)
                .collect(Collectors.toList());
        roulementDTO.setShiftIds(shiftIds);

        return roulementDTO;
    }
}
