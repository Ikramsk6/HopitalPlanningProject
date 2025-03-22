package com.example.HopitalPlanningProject.repositories;

import com.example.HopitalPlanningProject.model.NonPreferencePersonnel;
import com.example.HopitalPlanningProject.model.NonPreferencePersonnelId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NonPreferencePersonnelRepository extends JpaRepository<NonPreferencePersonnel, NonPreferencePersonnelId> {
}
