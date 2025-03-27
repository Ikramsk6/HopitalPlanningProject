package com.example.HopitalPlanningProject.controllers;

import com.example.HopitalPlanningProject.model.InterdictionPrecedent;
import com.example.HopitalPlanningProject.services.InterdictionPrecedentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interdictionsPrecedents")
public class InterdictionPrecedentController {

    @Autowired
    private InterdictionPrecedentService interdictionPrecedentService;

    @GetMapping
    public List<InterdictionPrecedent> getAllInterdictions() {
        return interdictionPrecedentService.getAllInterdictions();
    }

    @PostMapping
    public InterdictionPrecedent createInterdiction(@RequestBody InterdictionPrecedent interdictionPrecedent) {
        return interdictionPrecedentService.createInterdiction(interdictionPrecedent);
    }
}
