package com.example.HopitalPlanningProject.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Besoin {
    @EmbeddedId
    private BesoinId id;

    @Column(name = "Valeur_souhaitée")
    private short valeurSouhaitee;

}
