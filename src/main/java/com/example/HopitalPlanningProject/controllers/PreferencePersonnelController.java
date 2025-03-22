package com.example.HopitalPlanningProject.controllers;

import com.example.HopitalPlanningProject.model.PreferencePersonnel;
import com.example.HopitalPlanningProject.services.PreferencePersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/preference-personnel")
public class PreferencePersonnelController {

    @Autowired
    private PreferencePersonnelService service;

    @GetMapping
    public List<PreferencePersonnel> getAll() {
        return service.getAll();
    }

    @PostMapping
    public PreferencePersonnel create(@RequestBody PreferencePersonnel preference) {
        return service.create(preference);
    }
}
