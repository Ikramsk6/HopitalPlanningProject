package com.example.HopitalPlanningProject.erreurs;

public class ContratNotFoundException extends RuntimeException {
    public ContratNotFoundException(String message) {
        super(message);
    }
}
