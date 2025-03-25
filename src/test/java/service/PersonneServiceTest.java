package service;

import com.example.HopitalPlanningProject.model.Personne;
import com.example.HopitalPlanningProject.repositories.PersonneRepository;
import com.example.HopitalPlanningProject.services.PersonneService;
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
public class PersonneServiceTest {

    @Mock
    private PersonneRepository personneRepository;

    @InjectMocks
    private PersonneService personneService;

    private Personne personne1;
    private Personne personne2;

    @BeforeEach
    void setUp() {
        personne1 = new Personne(1, "Dupont", "Jean", true, null);
        personne2 = new Personne(2, "Martin", "Claire", false, null);
    }

    @Test
    void getAllPersonnes_shouldReturnList() {
        when(personneRepository.findAll()).thenReturn(Arrays.asList(personne1, personne2));

        List<Personne> result = personneService.getAllPersonnes();

        assertEquals(2, result.size());
        verify(personneRepository, times(1)).findAll();
    }

    @Test
    void getPersonneById_shouldReturnPersonneIfExists() {
        when(personneRepository.findById(1)).thenReturn(Optional.of(personne1));

        Optional<Personne> result = personneService.getPersonneById(1);

        assertTrue(result.isPresent());
        assertEquals("Dupont", result.get().getNom());
        verify(personneRepository, times(1)).findById(1);
    }

    @Test
    void getPersonneById_shouldReturnEmptyIfNotExists() {
        when(personneRepository.findById(3)).thenReturn(Optional.empty());

        Optional<Personne> result = personneService.getPersonneById(3);

        assertFalse(result.isPresent());
        verify(personneRepository, times(1)).findById(3);
    }

    @Test
    void savePersonne_shouldSaveAndReturnPersonne() {
        when(personneRepository.save(personne1)).thenReturn(personne1);

        Personne result = personneService.savePersonne(personne1);

        assertNotNull(result);
        assertEquals("Jean", result.getPrenom());
        verify(personneRepository, times(1)).save(personne1);
    }

    @Test
    void deletePersonne_shouldCallDeleteById() {
        personneService.deletePersonne(1);

        verify(personneRepository, times(1)).deleteById(1);
    }
}
