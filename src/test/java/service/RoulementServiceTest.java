package service;

import com.example.HopitalPlanningProject.model.Roulement;
import com.example.HopitalPlanningProject.repositories.RoulementRepository;
import com.example.HopitalPlanningProject.services.RoulementService;
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
public class RoulementServiceTest {

    @Mock
    private RoulementRepository roulementRepository;

    @InjectMocks
    private RoulementService roulementService;

    private Roulement roulement1;
    private Roulement roulement2;

    @BeforeEach
    void setUp() {
        roulement1 = new Roulement(1, (byte) 5, null);  // Remplacer par des valeurs valides si nécessaire
        roulement2 = new Roulement(2, (byte) 7, null);  // Remplacer par des valeurs valides si nécessaire
    }

    @Test
    void getAllRoulements_shouldReturnList() {
        when(roulementRepository.findAll()).thenReturn(Arrays.asList(roulement1, roulement2));

        List<Roulement> result = roulementService.getAllRoulements();

        assertEquals(2, result.size());
        verify(roulementRepository, times(1)).findAll();
    }

    @Test
    void getRoulementById_shouldReturnRoulementIfExists() {
        when(roulementRepository.findById(1)).thenReturn(Optional.of(roulement1));

        Optional<Roulement> result = roulementService.getRoulementById(1);

        assertTrue(result.isPresent());
        assertEquals(roulement1.getIdRoulement(), result.get().getIdRoulement());
        verify(roulementRepository, times(1)).findById(1);
    }

    @Test
    void getRoulementById_shouldReturnEmptyIfNotExists() {
        when(roulementRepository.findById(1)).thenReturn(Optional.empty());

        Optional<Roulement> result = roulementService.getRoulementById(1);

        assertFalse(result.isPresent());
        verify(roulementRepository, times(1)).findById(1);
    }

    @Test
    void saveRoulement_shouldSaveAndReturnRoulement() {
        when(roulementRepository.save(roulement1)).thenReturn(roulement1);

        Roulement result = roulementService.saveRoulement(roulement1);

        assertNotNull(result);
        assertEquals(roulement1.getIdRoulement(), result.getIdRoulement());
        verify(roulementRepository, times(1)).save(roulement1);
    }

    @Test
    void deleteRoulement_shouldCallDeleteById() {
        roulementService.deleteRoulement(1);

        verify(roulementRepository, times(1)).deleteById(1);
    }
}
