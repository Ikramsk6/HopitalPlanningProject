package com.example.HopitalPlanningProject.repositories;

import com.example.HopitalPlanningProject.model.InterdictionPrecedent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterdictionPrecedentRepository extends JpaRepository<InterdictionPrecedent, Integer> {
}
