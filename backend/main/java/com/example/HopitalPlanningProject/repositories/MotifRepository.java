package com.example.HopitalPlanningProject.repositories;

import com.example.HopitalPlanningProject.model.Motif;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotifRepository extends JpaRepository<Motif, String> {
}
