package com.example.HopitalPlanningProject.repositories;

import com.example.HopitalPlanningProject.models.Equipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipeRepository extends JpaRepository<Equipe, Long> {
}
