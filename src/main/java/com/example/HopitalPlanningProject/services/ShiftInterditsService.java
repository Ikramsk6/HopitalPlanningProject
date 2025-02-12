package com.example.HopitalPlanningProject.services;

import com.example.HopitalPlanningProject.models.ShiftInterdits;
import com.example.HopitalPlanningProject.repositories.ShiftInterditsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShiftInterditsService {
    private final ShiftInterditsRepository shiftInterditsRepository;

    public ShiftInterditsService(ShiftInterditsRepository shiftInterditsRepository) {
        this.shiftInterditsRepository = shiftInterditsRepository;
    }

    public List<ShiftInterdits> getAllShiftInterdits() {
        return shiftInterditsRepository.findAll();
    }

    public Optional<ShiftInterdits> getShiftInterditsById(Long id) {
        return shiftInterditsRepository.findById(id);
    }

    public ShiftInterdits saveShiftInterdits(ShiftInterdits shiftInterdits) {
        return shiftInterditsRepository.save(shiftInterdits);
    }

    public void deleteShiftInterdits(Long id) {
        shiftInterditsRepository.deleteById(id);
    }
}
