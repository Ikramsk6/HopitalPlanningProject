package com.example.HopitalPlanningProject.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@DiscriminatorValue("LEGAL") // Indique que cette contrainte est de type légal
public class ContrainteLegal extends Contrainte {

    private String type; // Type de contrainte (ex: "Durée max de travail")
    private String description; // Détails de la contrainte

    // 🔹 Vérifier si une contrainte légale est respectée
    public boolean verifierContrainte() {
        // Implémentation logique ici (exemple : vérifier si un employé dépasse les heures max)
        return true;
    }

    // 🔹 Ajouter une nouvelle contrainte légale
    public void ajouterContrainte(String description) {
        this.description = description;
    }

    // 🔹 Obtenir les détails de la contrainte
    public String getDetails() {
        return "Type : " + type + " - Description : " + description;
    }
}
