package com.example.HopitalPlanningProject.model;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class BesoinId implements Serializable {
    @Column(name = "idShift")
    private int idShift;

    @Column(name = "idJour")
    private int idJour; // VARCHAR(50) → String en Java
}
