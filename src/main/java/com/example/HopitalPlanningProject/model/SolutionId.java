package com.example.HopitalPlanningProject.model;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SolutionId implements Serializable {
    private int idPersonne;
    private int idRoulement;

    // Implémentation de equals et hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SolutionId that = (SolutionId) o;
        return idPersonne == that.idPersonne && idRoulement == that.idRoulement;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPersonne, idRoulement);
    }
}

