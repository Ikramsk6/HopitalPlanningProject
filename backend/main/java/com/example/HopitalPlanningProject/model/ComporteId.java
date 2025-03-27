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
public class ComporteId implements Serializable {
    private int idJour;
    private int idMotif;

    // Implémentation de equals et hashCode


    @Override
    public int hashCode() {
        return Objects.hash(idJour, idMotif);
    }
}
