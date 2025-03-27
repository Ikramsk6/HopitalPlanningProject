package com.example.HopitalPlanningProject.controllers;

import com.example.HopitalPlanningProject.model.Motif;
import com.example.HopitalPlanningProject.services.MotifService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/motifs")
public class MotifController {

    @Autowired
    private MotifService motifService;

    @GetMapping
    public List<Motif> getAllMotifs() {
        return motifService.getAllMotifs();
    }

    @PostMapping
    public Motif createMotif(@RequestBody Motif motif) {
        return motifService.createMotif(motif);
    }
}
