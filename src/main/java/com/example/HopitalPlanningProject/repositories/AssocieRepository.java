package com.example.HopitalPlanningProject.repositories;

import com.example.HopitalPlanningProject.model.Associe;
import com.example.HopitalPlanningProject.model.AssocieId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssocieRepository extends JpaRepository<Associe, AssocieId> {
}
