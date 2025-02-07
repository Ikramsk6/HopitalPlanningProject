package com.example.HopitalPlanningProject.repositories;

import com.example.HopitalPlanningProject.models.Contrainte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContrainteRepository extends JpaRepository<Contrainte, Long> {
}
