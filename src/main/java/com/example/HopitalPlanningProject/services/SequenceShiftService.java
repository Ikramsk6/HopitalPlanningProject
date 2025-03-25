package com.example.HopitalPlanningProject.services;

import com.example.HopitalPlanningProject.model.SequenceShift;
import com.example.HopitalPlanningProject.model.SequenceShiftId;
import com.example.HopitalPlanningProject.repositories.SequenceShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SequenceShiftService {

    private final SequenceShiftRepository sequenceShiftRepository;

    @Autowired
    public SequenceShiftService(SequenceShiftRepository sequenceShiftRepository) {
        this.sequenceShiftRepository = sequenceShiftRepository;
    }

    // Récupérer tous les SequenceShift
    public List<SequenceShift> getAllSequenceShifts() {
        return sequenceShiftRepository.findAll();
    }

    // Récupérer un SequenceShift par son ID composite
    public Optional<SequenceShift> getSequenceShiftById(SequenceShiftId id) {
        return sequenceShiftRepository.findById(id);
    }

    // Sauvegarder un SequenceShift
    public SequenceShift saveSequenceShift(SequenceShift sequenceShift) {
        return sequenceShiftRepository.save(sequenceShift);
    }

    // Supprimer un SequenceShift par son ID composite
    public void deleteSequenceShift(SequenceShiftId id) {
        sequenceShiftRepository.deleteById(id);
    }
    public void createSequence(SequenceShift sequenceShift) {
        sequenceShiftRepository.save(sequenceShift);
    }
}
