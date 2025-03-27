package com.example.HopitalPlanningProject.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class InterdictionPrecedent {
    @EmbeddedId
    private InterdictionPrecedentId id;

}
