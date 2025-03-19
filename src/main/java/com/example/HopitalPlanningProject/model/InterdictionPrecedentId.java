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
public class InterdictionPrecedentId implements Serializable {
    private int idShift;
    private int idShift1;

    // Implémentation de equals et hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InterdictionPrecedentId that = (InterdictionPrecedentId) o;
        return idShift == that.idShift && idShift1 == that.idShift1;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idShift, idShift1);
    }
}