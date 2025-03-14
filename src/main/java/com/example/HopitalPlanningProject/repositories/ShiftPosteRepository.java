package com.example.HopitalPlanningProject.repositories;

import com.example.HopitalPlanningProject.model.ShiftPoste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShiftPosteRepository extends JpaRepository<ShiftPoste, Integer> {
}
