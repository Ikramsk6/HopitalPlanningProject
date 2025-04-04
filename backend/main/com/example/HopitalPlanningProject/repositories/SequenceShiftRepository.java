package com.example.HopitalPlanningProject.repositories;

import com.example.HopitalPlanningProject.model.SequenceShift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SequenceShiftRepository extends JpaRepository<SequenceShift, Integer> {
}
