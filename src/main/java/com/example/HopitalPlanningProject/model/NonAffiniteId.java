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
public class NonAffiniteId implements Serializable {
    private int idPersonne;
    private int idPersonne1;

    // Implémentation de equals et hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NonAffiniteId that = (NonAffiniteId) o;
        return idPersonne == that.idPersonne && idPersonne1 == that.idPersonne1;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPersonne, idPersonne1);
    }
}
