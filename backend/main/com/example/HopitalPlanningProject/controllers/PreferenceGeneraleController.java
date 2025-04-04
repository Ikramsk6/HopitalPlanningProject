package com.example.HopitalPlanningProject.controllers;

import com.example.HopitalPlanningProject.model.PreferenceGenerale;
import com.example.HopitalPlanningProject.services.PreferenceGeneraleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/preferencesGenerales")
public class PreferenceGeneraleController {

    @Autowired
    private PreferenceGeneraleService preferenceGeneraleService;

    @GetMapping
    public List<PreferenceGenerale> getAllPreferences() {
        return preferenceGeneraleService.getAllPreferences();
    }

    @PostMapping
    public PreferenceGenerale createPreference(@RequestBody PreferenceGenerale preferenceGenerale) {
        return preferenceGeneraleService.createPreference(preferenceGenerale);
    }
}
