package service;

import com.example.HopitalPlanningProject.model.Equipe;
import com.example.HopitalPlanningProject.repositories.EquipeRepository;
import com.example.HopitalPlanningProject.services.EquipeService;
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
public class EquipeServiceTest {

    @Mock
    private EquipeRepository equipeRepository;

    @InjectMocks
    private EquipeService equipeService;

    private Equipe equipe1;
    private Equipe equipe2;

    @BeforeEach
    void setUp() {
        equipe1 = new Equipe();
        equipe1.setId(1);
        equipe1.setNom("Équipe A");

        equipe2 = new Equipe();
        equipe2.setId(2);
        equipe2.setNom("Équipe B");
    }

    @Test
    void getAllEquipes_shouldReturnListOfEquipes() {
        when(equipeRepository.findAll()).thenReturn(Arrays.asList(equipe1, equipe2));

        List<Equipe> equipes = equipeService.getAllEquipes();

        assertEquals(2, equipes.size());
        assertEquals("Équipe A", equipes.get(0).getNom());
        assertEquals("Équipe B", equipes.get(1).getNom());
        verify(equipeRepository, times(1)).findAll();
    }

    @Test
    void getEquipeById_shouldReturnEquipeIfExists() {
        when(equipeRepository.findById(1)).thenReturn(Optional.of(equipe1));

        Optional<Equipe> foundEquipe = equipeService.getEquipeById(1);

        assertTrue(foundEquipe.isPresent());
        assertEquals("Équipe A", foundEquipe.get().getNom());
        verify(equipeRepository, times(1)).findById(1);
    }

    @Test
    void getEquipeById_shouldReturnEmptyIfNotExists() {
        when(equipeRepository.findById(99)).thenReturn(Optional.empty());

        Optional<Equipe> foundEquipe = equipeService.getEquipeById(99);

        assertFalse(foundEquipe.isPresent());
        verify(equipeRepository, times(1)).findById(99);
    }

    @Test
    void saveEquipe_shouldSaveAndReturnEquipe() {
        when(equipeRepository.save(equipe1)).thenReturn(equipe1);

        Equipe savedEquipe = equipeService.saveEquipe(equipe1);

        assertNotNull(savedEquipe);
        assertEquals("Équipe A", savedEquipe.getNom());
        verify(equipeRepository, times(1)).save(equipe1);
    }

    @Test
    void deleteEquipe_shouldCallDeleteById() {
        equipeService.deleteEquipe(1);

        verify(equipeRepository, times(1)).deleteById(1);
    }
}
