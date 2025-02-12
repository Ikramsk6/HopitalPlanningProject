package com.example.HopitalPlanningProject.repositories;

import com.example.HopitalPlanningProject.models.ContrainteLegal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContrainteLegalRepository extends JpaRepository<ContrainteLegal, Long> {
}
