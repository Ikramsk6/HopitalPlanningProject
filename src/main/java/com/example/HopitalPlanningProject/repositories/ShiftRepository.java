package com.example.HopitalPlanningProject.repositories;

import com.example.HopitalPlanningProject.models.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, Long> {
}
