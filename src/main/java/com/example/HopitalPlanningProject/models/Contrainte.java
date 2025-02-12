package com.example.HopitalPlanningProject.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Héritage avec une seule table pour stocker toutes les contraintes
@DiscriminatorColumn(name = "dtype") // Ajoute une colonne "dtype" pour différencier les sous-classes
public abstract class Contrainte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
