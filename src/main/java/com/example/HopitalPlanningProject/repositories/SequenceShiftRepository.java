package com.example.HopitalPlanningProject.repositories;

import com.example.HopitalPlanningProject.model.SequenceShift;
import com.example.HopitalPlanningProject.model.SequenceShiftId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SequenceShiftRepository extends JpaRepository<SequenceShift, SequenceShiftId> {
    // Méthodes supplémentaires si nécessaire (par exemple, rechercher par ordre)
}
