package com.example.HopitalPlanningProject.repositories;

import com.example.HopitalPlanningProject.models.Roulement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoulementRepository extends JpaRepository<Roulement, Long> {
}
