package com.example.HopitalPlanningProject.services;

import com.example.HopitalPlanningProject.model.Shift;
import com.example.HopitalPlanningProject.repositories.ShiftRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service pour gérer les shifts.
 */
@Service
public class ShiftService {
    private final ShiftRepository shiftRepository;

    public ShiftService(ShiftRepository shiftRepository) {
        this.shiftRepository = shiftRepository;
    }

    public List<Shift> getAllShifts() {
        return shiftRepository.findAll();
    }

    public Optional<Shift> getShiftById(int id) {
        return shiftRepository.findById(id);
    }

    public Shift saveShift(Shift shift) {
        return shiftRepository.save(shift);
    }

    public void deleteShift(int id) {
        shiftRepository.deleteById(id);
    }
}
