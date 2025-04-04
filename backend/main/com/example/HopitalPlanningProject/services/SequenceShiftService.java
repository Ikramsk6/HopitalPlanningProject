package com.example.HopitalPlanningProject.services;

import com.example.HopitalPlanningProject.model.SequenceShift;
import com.example.HopitalPlanningProject.repositories.SequenceShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SequenceShiftService {

    @Autowired
    private SequenceShiftRepository sequenceShiftRepository;

    public List<SequenceShift> getAllSequences() {
        return sequenceShiftRepository.findAll();
    }

    public Optional<SequenceShift> getSequenceById(int id) {
        return sequenceShiftRepository.findById(id);
    }

    public SequenceShift createSequence(SequenceShift sequenceShift) {
        return sequenceShiftRepository.save(sequenceShift);
    }

    public void deleteSequence(int id) {
        sequenceShiftRepository.deleteById(id);
    }
}

