package service;

import com.example.HopitalPlanningProject.model.Contrat;
import com.example.HopitalPlanningProject.repositories.ContratRepository;
import com.example.HopitalPlanningProject.services.ContratService;
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
public class ContratServiceTest {

    @Mock
    private ContratRepository contratRepository;

    @InjectMocks
    private ContratService contratService;

    private Contrat contrat1;
    private Contrat contrat2;

    @BeforeEach
    void setUp() {
        contrat1 = new Contrat();
        contrat1.setId(1);
        contrat1.setPourcentageTravail(100.0);
        contrat1.setDescriptionContrat("Contrat à temps plein");

        contrat2 = new Contrat();
        contrat2.setId(2);
        contrat2.setPourcentageTravail(80.0);
        contrat2.setDescriptionContrat("Contrat à temps partiel");
    }

    @Test
    void getAllContrats_shouldReturnListOfContrats() {
        when(contratRepository.findAll()).thenReturn(Arrays.asList(contrat1, contrat2));

        List<Contrat> contrats = contratService.getAllContrats();

        assertEquals(2, contrats.size());
        assertEquals("Contrat à temps plein", contrats.get(0).getDescriptionContrat());
        assertEquals("Contrat à temps partiel", contrats.get(1).getDescriptionContrat());
        verify(contratRepository, times(1)).findAll();
    }

    @Test
    void getContratById_shouldReturnContratIfExists() {
        when(contratRepository.findById(1)).thenReturn(Optional.of(contrat1));

        Optional<Contrat> foundContrat = contratService.getContratById(1);

        assertTrue(foundContrat.isPresent());
        assertEquals("Contrat à temps plein", foundContrat.get().getDescriptionContrat());
        verify(contratRepository, times(1)).findById(1);
    }

    @Test
    void getContratById_shouldReturnEmptyIfNotExists() {
        when(contratRepository.findById(99)).thenReturn(Optional.empty());

        Optional<Contrat> foundContrat = contratService.getContratById(99);

        assertFalse(foundContrat.isPresent());
        verify(contratRepository, times(1)).findById(99);
    }

    @Test
    void saveContrat_shouldSaveAndReturnContrat() {
        when(contratRepository.save(contrat1)).thenReturn(contrat1);

        Contrat savedContrat = contratService.saveContrat(contrat1);

        assertNotNull(savedContrat);
        assertEquals("Contrat à temps plein", savedContrat.getDescriptionContrat());
        verify(contratRepository, times(1)).save(contrat1);
    }

    @Test
    void deleteContrat_shouldCallDeleteById() {
        contratService.deleteContrat(1);

        verify(contratRepository, times(1)).deleteById(1);
    }
}
