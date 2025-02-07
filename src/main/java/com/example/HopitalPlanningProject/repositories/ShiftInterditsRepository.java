package com.example.HopitalPlanningProject.repositories;

import com.example.HopitalPlanningProject.models.ShiftInterdits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShiftInterditsRepository extends JpaRepository<ShiftInterdits, Long> {
}
