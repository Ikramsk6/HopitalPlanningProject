package com.example.HopitalPlanningProject.services;

import com.example.HopitalPlanningProject.models.Shift;
import com.example.HopitalPlanningProject.repositories.ShiftRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShiftService {
    private final ShiftRepository shiftRepository;

    public ShiftService(ShiftRepository shiftRepository) {
        this.shiftRepository = shiftRepository;
    }

    public List<Shift> getAllShifts() {
        return shiftRepository.findAll();
    }

    public Optional<Shift> getShiftById(Long id) {
        return shiftRepository.findById(id);
    }

    public Shift createShift(Shift shift) {
        return shiftRepository.save(shift);
    }

    public Shift updateShift(Long id, Shift shiftDetails) {
        return shiftRepository.findById(id).map(shift -> {
            shift.setNom(shiftDetails.getNom());
            shift.setType(shiftDetails.getType());
            return shiftRepository.save(shift);
        }).orElse(null);
    }

    public void deleteShift(Long id) {
        shiftRepository.deleteById(id);
    }
}
