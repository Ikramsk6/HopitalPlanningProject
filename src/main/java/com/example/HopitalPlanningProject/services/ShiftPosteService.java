
package com.example.HopitalPlanningProject.services;

import com.example.HopitalPlanningProject.model.ShiftPoste;
import com.example.HopitalPlanningProject.repositories.ShiftPosteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShiftPosteService {

    @Autowired
    private ShiftPosteRepository shiftPosteRepository;

    public List<ShiftPoste> getAllShifts() {
        return shiftPosteRepository.findAll();
    }

    public Optional<ShiftPoste> getShiftById(int id) {
        return shiftPosteRepository.findById(id);
    }

    public ShiftPoste createShift(ShiftPoste shiftPoste) {
        return shiftPosteRepository.save(shiftPoste);
    }

    public void deleteShift(int id) {
        shiftPosteRepository.deleteById(id);
    }
}
