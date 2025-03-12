package com.example.HopitalPlanningProject.services;

import com.example.HopitalPlanningProject.models.ShiftInterdits;
import com.example.HopitalPlanningProject.repositories.ShiftInterditsRepository;
import com.example.HopitalPlanningProject.models.Shift;
import com.example.HopitalPlanningProject.repositories.ShiftRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class ShiftInterditsService {
    private final ShiftInterditsRepository shiftInterditsRepository;
    private final ShiftRepository shiftRepository; // Ajoute le repository des shifts

    public ShiftInterditsService(ShiftInterditsRepository shiftInterditsRepository, ShiftRepository shiftRepository) {
        this.shiftInterditsRepository = shiftInterditsRepository;
        this.shiftRepository = shiftRepository;
    }

    public ShiftInterdits saveShiftInterdits(ShiftInterdits shiftInterdits) {
        List<Shift> savedShifts = new ArrayList<>();
        for (Shift shift : shiftInterdits.getShiftsInterdits()) {
            Optional<Shift> existingShift = shiftRepository.findByNomAndType(shift.getNom(), shift.getType());
            savedShifts.add(existingShift.orElseGet(() -> shiftRepository.save(shift)));
        }
        shiftInterdits.setShiftsInterdits(savedShifts);
        return shiftInterditsRepository.save(shiftInterdits);
    }

    public List<ShiftInterdits> getAllShiftInterdits() {
        return shiftInterditsRepository.findAll();
    }

    public Optional<ShiftInterdits> getShiftInterditsById(Long id) {
        return shiftInterditsRepository.findById(id);
    }



    public void deleteShiftInterdits(Long id) {
        shiftInterditsRepository.deleteById(id);
    }
}
