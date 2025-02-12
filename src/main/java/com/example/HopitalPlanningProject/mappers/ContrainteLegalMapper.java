package com.example.HopitalPlanningProject.mappers;

import com.example.HopitalPlanningProject.DTO.ContrainteLegalDTO;
import com.example.HopitalPlanningProject.models.ContrainteLegal;

public class ContrainteLegalMapper {

    public static ContrainteLegalDTO toDTO(ContrainteLegal contrainteLegal) {
        if (contrainteLegal == null) {
            return null;
        }
        ContrainteLegalDTO dto = new ContrainteLegalDTO();
        dto.setId(contrainteLegal.getId());
        dto.setType(contrainteLegal.getType());
        dto.setDescription(contrainteLegal.getDescription());
        return dto;
    }

    public static ContrainteLegal toEntity(ContrainteLegalDTO contrainteLegalDTO) {
        if (contrainteLegalDTO == null) {
            return null;
        }
        ContrainteLegal contrainteLegal = new ContrainteLegal();
        contrainteLegal.setId(contrainteLegalDTO.getId());
        contrainteLegal.setType(contrainteLegalDTO.getType());
        contrainteLegal.setDescription(contrainteLegalDTO.getDescription());
        return contrainteLegal;
    }
}
