package com.example.HopitalPlanningProject.repositories;

import com.example.HopitalPlanningProject.models.Personne;
import com.example.HopitalPlanningProject.models.Preference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PreferenceRepository extends JpaRepository<Preference, Long> {
    public List<Preference> findByPersonne(Personne personne);
}
