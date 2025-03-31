package com.example.HopitalPlanningProject.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ShiftPoste {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idShift")
    private int idShift;

    @Column(name = "Travail")
    private boolean travail; // LOGICAL → boolean en Java

    @Column(name = "Tag", length = 3, nullable = false)
    private String tag;

    @Column(name = "Type", length = 50, nullable = false)
    private String type;

    @Column(name = "Poste", length = 50, nullable = false)
    private String poste;

    // Relation OneToMany avec InterdictionPrecedent
    @OneToMany(mappedBy = "id.idShift", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<InterdictionPrecedent> interdictionsPrecedentes;
}
