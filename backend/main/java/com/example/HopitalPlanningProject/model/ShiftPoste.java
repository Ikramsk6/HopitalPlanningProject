package com.example.HopitalPlanningProject.model;

import com.example.HopitalPlanningProject.model.InterdictionPrecedent;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idShift")
public class ShiftPoste {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idShift")
    private int idShift;

    @Column(name = "Travail")
    private boolean travail;

    @Column(name = "Tag", length = 3, nullable = false)
    private String tag;

    @Column(name = "Type", length = 50, nullable = false)
    private String type;

    @Column(name = "Poste", length = 50, nullable = false)
    private String poste;

    @OneToMany(mappedBy = "shiftPoste", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InterdictionPrecedent> interdictionsPrecedentes;
}
