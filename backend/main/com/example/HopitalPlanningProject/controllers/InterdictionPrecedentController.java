package com.example.HopitalPlanningProject.controllers;

import com.example.HopitalPlanningProject.model.InterdictionPrecedent;
import com.example.HopitalPlanningProject.model.InterdictionPrecedentId;
import com.example.HopitalPlanningProject.services.InterdictionPrecedentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/interdictionsPrecedents")
public class InterdictionPrecedentController {

    @Autowired
    private InterdictionPrecedentService interdictionPrecedentService;

    @GetMapping
    public List<InterdictionPrecedent> getAllInterdictions() {
        return interdictionPrecedentService.getAllInterdictions();
    }

    @GetMapping("/{idShift}/{idShift1}")
    public Optional<InterdictionPrecedent> getInterdictionById(@PathVariable int idShift, @PathVariable int idShift1) {
        return interdictionPrecedentService.getInterdictionById(new InterdictionPrecedentId(idShift, idShift1));
    }

    @PostMapping
    public InterdictionPrecedent createInterdiction(@RequestBody InterdictionPrecedent interdictionPrecedent) {
        return interdictionPrecedentService.createInterdiction(interdictionPrecedent);
    }



    @DeleteMapping("/{idShift}/{idShift1}")
    public void deleteInterdiction(@PathVariable int idShift, @PathVariable int idShift1) {
        interdictionPrecedentService.deleteInterdiction(new InterdictionPrecedentId(idShift, idShift1));
    }
}
