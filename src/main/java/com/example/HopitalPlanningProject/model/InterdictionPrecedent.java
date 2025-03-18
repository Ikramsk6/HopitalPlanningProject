package com.example.HopitalPlanningProject.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class InterdictionPrecedent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idShift")
    private ShiftPoste shift;

    @ManyToOne
    @JoinColumn(name = "idShift_1")
    private ShiftPoste shift1;
}
