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
public class AssocieId implements Serializable {
    private int idShift;
    private int idMotif;

    @Override
    public int hashCode() {
        return Objects.hash(idShift, idMotif);
    }
}
