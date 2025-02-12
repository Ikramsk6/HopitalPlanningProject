package com.example.HopitalPlanningProject.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@DiscriminatorValue("SHIFT_INTERDITS") // Indique que cette contrainte concerne les shifts interdits
public class ShiftInterdits extends Contrainte {

    @ManyToMany
    @JoinTable(
            name = "shift_interdits",
            joinColumns = @JoinColumn(name = "contrainte_id"),
            inverseJoinColumns = @JoinColumn(name = "shift_id")
    )
    private List<Shift> shiftsInterdits; // Liste des shifts qui ne peuvent pas être enchaînés

    //  Obtenir les shifts interdits sous forme de texte
    public String getDetails() {
        return "Shifts interdits : " + shiftsInterdits.toString();
    }
}
