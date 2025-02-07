package com.example.HopitalPlanningProject.repositories;

import com.example.HopitalPlanningProject.models.Planning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanningRepository extends JpaRepository<Planning, Long> {
}
