package com.example.HopitalPlanningProject.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class InterdictionPrecedent {
    @EmbeddedId
    private InterdictionPrecedentId id;

    @ManyToOne
    @MapsId("idShift")
    @JoinColumn(name = "id_shift")
    private ShiftPoste shiftPoste;

    @ManyToOne
    @MapsId("idShift1")
    @JoinColumn(name = "id_shift1")
    private ShiftPoste shiftPoste1;
}
