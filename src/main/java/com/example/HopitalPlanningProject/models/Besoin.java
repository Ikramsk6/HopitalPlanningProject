package com.example.HopitalPlanningProject.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Besoin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    private Map<Long, Integer> besoinsParShift = new HashMap<>(); // Shift ID -> Nombre de personnes

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "shift_id", nullable = false)
    private Shift shift;
}
