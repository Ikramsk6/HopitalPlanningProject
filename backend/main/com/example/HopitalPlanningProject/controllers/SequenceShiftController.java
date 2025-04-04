package com.example.HopitalPlanningProject.controllers;

import com.example.HopitalPlanningProject.model.SequenceShift;
import com.example.HopitalPlanningProject.services.SequenceShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sequenceShifts")
public class SequenceShiftController {

    @Autowired
    private SequenceShiftService sequenceShiftService;

    @GetMapping
    public List<SequenceShift> getAllSequences() {
        return sequenceShiftService.getAllSequences();
    }

    @PostMapping
    public SequenceShift createSequence(@RequestBody SequenceShift sequenceShift) {
        return sequenceShiftService.createSequence(sequenceShift);
    }
}
