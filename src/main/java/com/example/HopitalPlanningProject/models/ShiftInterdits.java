package com.example.HopitalPlanningProject.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.ArrayList;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@DiscriminatorValue("SHIFT_INTERDITS")
public class ShiftInterdits extends Contrainte {

    @ManyToMany(cascade = CascadeType.ALL)  //  Ajout du cascade
    @JoinTable(
            name = "shift_interdits",
            joinColumns = @JoinColumn(name = "contrainte_id"),
            inverseJoinColumns = @JoinColumn(name = "shift_id")
    )
    private List<Shift> shiftsInterdits = new ArrayList<>();

    public String getDetails() {
        return "Shifts interdits : " + shiftsInterdits.toString();
    }
}
