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
public class PreferenceGeneraleId implements Serializable {
    private int idContrat;
    private int idShift;
    private int idJour;

    // Implémentation de equals et hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PreferenceGeneraleId that = (PreferenceGeneraleId) o;
        return idContrat == that.idContrat && idShift == that.idShift && Objects.equals(idJour, that.idJour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idContrat, idShift, idJour);
    }
}

