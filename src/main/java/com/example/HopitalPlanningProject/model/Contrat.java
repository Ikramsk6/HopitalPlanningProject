package com.example.HopitalPlanningProject.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Contrat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contrat")
    private int idContrat;

    @Setter
    @Getter
    @Column(name = "pourcentage_travail", nullable = false)
    private double pourcentageTravail; // Ex: 100.00, 80.00

    @Setter
    @Getter
    @Column(name = "description_contrat", nullable = false)
    private String descriptionContrat;

    @OneToOne
    @JoinColumn(name = "id_roulement", nullable = false, unique = true)
    private Roulement roulement;

    // Méthodes utilisées dans les tests
    public int getId() {
        return idContrat;
    }

    public void setId(int id) {
        this.idContrat = id;
    }

}
