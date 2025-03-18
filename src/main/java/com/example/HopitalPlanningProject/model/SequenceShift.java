package com.example.HopitalPlanningProject.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SequenceShift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "idRoulement")
    private Roulement roulement;

    @ManyToOne
    @JoinColumn(name = "idShift")
    private ShiftPoste shift;

    private int ordre;
}
