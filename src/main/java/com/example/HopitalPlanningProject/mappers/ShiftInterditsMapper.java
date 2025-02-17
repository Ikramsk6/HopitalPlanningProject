package com.example.HopitalPlanningProject.mappers;
import com.example.HopitalPlanningProject.DTO.ShiftInterditsDTO;
import com.example.HopitalPlanningProject.models.ShiftInterdits;

// import java.util.List;
import java.util.stream.Collectors;

public class ShiftInterditsMapper {

    // Mapper de l'entité vers le DTO
    public static ShiftInterditsDTO toDTO(ShiftInterdits shiftInterdits) {
        ShiftInterditsDTO dto = new ShiftInterditsDTO();
        dto.setId(shiftInterdits.getId());
        dto.setShiftIds(shiftInterdits.getShiftsInterdits().stream()
                .map(shift -> shift.getId()) // Utilisation de l'ID des shifts
                .collect(Collectors.toList()));
        return dto;
    }

    // Mapper du DTO vers l'entité
    public static ShiftInterdits toEntity(ShiftInterditsDTO dto) {
        ShiftInterdits entity = new ShiftInterdits();
        entity.setId(dto.getId());
        // Le mappage inverse des IDs des shifts vers des objets Shift pourrait se faire
        // en fonction de ta logique (peut-être via un service ou une méthode spécifique).
        // Par exemple, ici, on ne fait que peu de logique, mais il faudra gérer la récupération
        // des objets Shift à partir des IDs dans la base de données.
        return entity;
    }
}
