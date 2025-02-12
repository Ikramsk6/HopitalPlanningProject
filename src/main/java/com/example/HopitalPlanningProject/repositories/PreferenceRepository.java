package com.example.HopitalPlanningProject.repositories;

import com.example.HopitalPlanningProject.models.Preference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PreferenceRepository extends JpaRepository<Preference, Long> {
    List<Preference> findByPersonneId(Long personneId);
}
