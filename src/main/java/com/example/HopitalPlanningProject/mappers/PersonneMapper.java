package com.example.HopitalPlanningProject.mappers;
import com.example.HopitalPlanningProject.DTO.PersonneDTO;
import com.example.HopitalPlanningProject.models.Personne;

public class PersonneMapper {

    // Convertir un PersonneDTO en Personne
    public static Personne toEntity(PersonneDTO personneDTO) {
        if (personneDTO == null) {
            return null;
        }

        Personne personne = new Personne();
        personne.setId(personneDTO.getId());
        personne.setNom(personneDTO.getNom());
        personne.setTypeContrat(personneDTO.getTypeContrat());

        return personne;
    }

    // Convertir un Personne en PersonneDTO
    public static PersonneDTO toDTO(Personne personne) {
        if (personne == null) {
            return null;
        }

        PersonneDTO personneDTO = new PersonneDTO();
        personneDTO.setId(personne.getId());
        personneDTO.setNom(personne.getNom());
        personneDTO.setTypeContrat(personne.getTypeContrat());

        return personneDTO;
    }
}
