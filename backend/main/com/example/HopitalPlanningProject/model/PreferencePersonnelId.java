package com.example.HopitalPlanningProject.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Embeddable
public class PreferencePersonnelId implements Serializable {

    @Column(name = "idPersonne")
    private int idPersonne;

    @Column(name = "idShift")
    private int idShift;

    @Column(name = "idJour")
    private int idJour;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PreferencePersonnelId that = (PreferencePersonnelId) o;
        return idPersonne == that.idPersonne && idShift == that.idShift && idJour == that.idJour;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPersonne, idShift, idJour);
    }
}
