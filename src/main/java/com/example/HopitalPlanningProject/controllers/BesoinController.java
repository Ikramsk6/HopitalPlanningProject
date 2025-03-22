package com.example.HopitalPlanningProject.controllers;

import com.example.HopitalPlanningProject.model.Besoin;
import com.example.HopitalPlanningProject.services.BesoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/besoins")
public class BesoinController {

    @Autowired
    private BesoinService service;

    @GetMapping
    public List<Besoin> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Besoin create(@RequestBody Besoin besoin) {
        return service.create(besoin);
    }
}
