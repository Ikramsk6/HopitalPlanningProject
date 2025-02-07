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
public class Planning {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private List<Roulement> roulements;

    @OneToMany
    private List<Besoin> besoins;

    @ElementCollection
    private List<String> contraintes;
}
