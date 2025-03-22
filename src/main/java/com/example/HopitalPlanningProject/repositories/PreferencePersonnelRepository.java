package com.example.HopitalPlanningProject.repositories;

import com.example.HopitalPlanningProject.model.PreferencePersonnel;
import com.example.HopitalPlanningProject.model.PreferencePersonnelId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreferencePersonnelRepository extends JpaRepository<PreferencePersonnel, PreferencePersonnelId> {
}
