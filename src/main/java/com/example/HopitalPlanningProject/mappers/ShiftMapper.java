package com.example.HopitalPlanningProject.mappers;
import com.example.HopitalPlanningProject.DTO.ShiftDTO;
import com.example.HopitalPlanningProject.models.Shift;

public class ShiftMapper {

    // Convertir un ShiftDTO en Shift
    public static Shift toEntity(ShiftDTO shiftDTO) {
        if (shiftDTO == null) {
            return null;
        }

        Shift shift = new Shift();
        shift.setId(shiftDTO.getId());
        shift.setNom(shiftDTO.getNom());
        shift.setType(shiftDTO.getType());

        return shift;
    }

    // Convertir un Shift en ShiftDTO
    public static ShiftDTO toDTO(Shift shift) {
        if (shift == null) {
            return null;
        }

        ShiftDTO shiftDTO = new ShiftDTO();
        shiftDTO.setId(shift.getId());
        shiftDTO.setNom(shift.getNom());
        shiftDTO.setType(shift.getType());

        return shiftDTO;
    }
}
