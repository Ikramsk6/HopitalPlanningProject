package com.example.HopitalPlanningProject.mappers;

import com.example.HopitalPlanningProject.DTO.BesoinDTO;
import com.example.HopitalPlanningProject.models.Besoin;

public class BesoinMapper {

    public static BesoinDTO toDTO(Besoin besoin) {
        if (besoin == null) {
            return null;
        }
        BesoinDTO dto = new BesoinDTO();
        dto.setId(besoin.getId());
        dto.setBesoinsParShift(besoin.getBesoinsParShift());
        return dto;
    }

    public static Besoin toEntity(BesoinDTO besoinDTO) {
        if (besoinDTO == null) {
            return null;
        }
        Besoin besoin = new Besoin();
        besoin.setId(besoinDTO.getId());
        besoin.setBesoinsParShift(besoinDTO.getBesoinsParShift());
        return besoin;
    }
}
