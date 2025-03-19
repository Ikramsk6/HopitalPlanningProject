package com.example.HopitalPlanningProject.repositories;

import com.example.HopitalPlanningProject.model.ShiftPoste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository pour l'entité Shift.
 */
@Repository
public interface ShiftRepository extends JpaRepository<ShiftPoste, Integer> {
}
