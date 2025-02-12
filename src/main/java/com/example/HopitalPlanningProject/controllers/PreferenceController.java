package com.example.HopitalPlanningProject.controllers;

import com.example.HopitalPlanningProject.models.Preference;
import com.example.HopitalPlanningProject.services.PreferenceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/preferences")
public class PreferenceController {
    private final PreferenceService preferenceService;

    public PreferenceController(PreferenceService preferenceService) {
        this.preferenceService = preferenceService;
    }

    @GetMapping
    public List<Preference> getAllPreferences() {
        return preferenceService.getAllPreferences();
    }

    @GetMapping("/{id}")
    public Optional<Preference> getPreferenceById(@PathVariable Long id) {
        return preferenceService.getPreferenceById(id);
    }

    @PostMapping
    public Preference createPreference(@RequestParam Long shiftId, @RequestParam String jour, @RequestParam int ecart) {
        return preferenceService.createPreference(shiftId, jour, ecart);
    }

    @PutMapping("/{id}")
    public Preference updatePreference(@PathVariable Long id, @RequestParam String jour, @RequestParam int ecart) {
        return preferenceService.updatePreference(id, jour, ecart);
    }

    @DeleteMapping("/{id}")
    public void deletePreference(@PathVariable Long id) {
        preferenceService.deletePreference(id);
    }
}
