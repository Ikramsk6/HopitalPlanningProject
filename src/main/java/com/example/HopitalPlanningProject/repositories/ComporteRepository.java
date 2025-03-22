package com.example.HopitalPlanningProject.repositories;

import com.example.HopitalPlanningProject.model.Comporte;
import com.example.HopitalPlanningProject.model.ComporteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComporteRepository extends JpaRepository<Comporte, ComporteId> {
}
