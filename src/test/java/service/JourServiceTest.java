package com.example.HopitalPlanningProject.services;

import com.example.HopitalPlanningProject.model.Jour;
import com.example.HopitalPlanningProject.repositories.JourRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class JourServiceTest {

    @Mock
    private JourRepository jourRepository;

    @InjectMocks
    private JourService jourService;

    private Jour jour1;
    private Jour jour2;

    @BeforeEach
    void setUp() {
        jour1 = new Jour(1, "Lundi");
        jour2 = new Jour(2, "Mardi");
    }

    @Test
    void getAllJours_shouldReturnListOfJours() {
        when(jourRepository.findAll()).thenReturn(Arrays.asList(jour1, jour2));

        List<Jour> result = jourService.getAllJours();

        assertEquals(2, result.size());
        assertTrue(result.contains(jour1));
        assertTrue(result.contains(jour2));
        verify(jourRepository, times(1)).findAll();
    }

    @Test
    void getJourById_shouldReturnJourIfExists() {
        when(jourRepository.findById(1)).thenReturn(Optional.of(jour1));

        Optional<Jour> result = jourService.getJourById(1);

        assertTrue(result.isPresent());
        assertEquals(jour1, result.get());
        verify(jourRepository, times(1)).findById(1);
    }

    @Test
    void getJourById_shouldReturnEmptyIfNotExists() {
        when(jourRepository.findById(3)).thenReturn(Optional.empty());

        Optional<Jour> result = jourService.getJourById(3);

        assertFalse(result.isPresent());
        verify(jourRepository, times(1)).findById(3);
    }

    @Test
    void saveJour_shouldSaveAndReturnJour() {
        when(jourRepository.save(jour1)).thenReturn(jour1);

        Jour result = jourService.saveJour(jour1);

        assertNotNull(result);
        assertEquals(jour1, result);
        verify(jourRepository, times(1)).save(jour1);
    }

    @Test
    void deleteJour_shouldCallDeleteById() {
        doNothing().when(jourRepository).deleteById(1);

        jourService.deleteJour(1);

        verify(jourRepository, times(1)).deleteById(1);
    }
}
