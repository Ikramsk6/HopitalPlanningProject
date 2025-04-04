package com.example.HopitalPlanningProject.repositories;

import com.example.HopitalPlanningProject.model.PreferenceGenerale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreferenceGeneraleRepository extends JpaRepository<PreferenceGenerale, Integer> {
}
