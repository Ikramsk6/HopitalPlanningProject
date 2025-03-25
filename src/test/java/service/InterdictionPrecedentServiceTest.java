package service;

import com.example.HopitalPlanningProject.model.InterdictionPrecedent;
import com.example.HopitalPlanningProject.model.InterdictionPrecedentId;
import com.example.HopitalPlanningProject.repositories.InterdictionPrecedentRepository;
import com.example.HopitalPlanningProject.services.InterdictionPrecedentService;
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
public class InterdictionPrecedentServiceTest {

    @Mock
    private InterdictionPrecedentRepository interdictionPrecedentRepository;

    @InjectMocks
    private InterdictionPrecedentService interdictionPrecedentService;

    private InterdictionPrecedent interdiction1;
    private InterdictionPrecedent interdiction2;
    private InterdictionPrecedentId id1;
    private InterdictionPrecedentId id2;

    @BeforeEach
    void setUp() {
        id1 = new InterdictionPrecedentId(1, 2);
        id2 = new InterdictionPrecedentId(3, 4);

        interdiction1 = new InterdictionPrecedent();
        interdiction1.setId(id1);

        interdiction2 = new InterdictionPrecedent();
        interdiction2.setId(id2);
    }

    @Test
    void getAllInterdictions_shouldReturnList() {
        when(interdictionPrecedentRepository.findAll()).thenReturn(Arrays.asList(interdiction1, interdiction2));

        List<InterdictionPrecedent> result = interdictionPrecedentService.getAllInterdictions();

        assertEquals(2, result.size());
        verify(interdictionPrecedentRepository, times(1)).findAll();
    }

    @Test
    void getInterdictionById_shouldReturnInterdictionIfExists() {
        when(interdictionPrecedentRepository.findById(id1)).thenReturn(Optional.of(interdiction1));

        Optional<InterdictionPrecedent> result = interdictionPrecedentService.getInterdictionById(id1);

        assertTrue(result.isPresent());
        assertEquals(id1, result.get().getId());
        verify(interdictionPrecedentRepository, times(1)).findById(id1);
    }

    @Test
    void getInterdictionById_shouldReturnEmptyIfNotExists() {
        when(interdictionPrecedentRepository.findById(id1)).thenReturn(Optional.empty());

        Optional<InterdictionPrecedent> result = interdictionPrecedentService.getInterdictionById(id1);

        assertFalse(result.isPresent());
        verify(interdictionPrecedentRepository, times(1)).findById(id1);
    }

    @Test
    void createInterdiction_shouldSaveAndReturnInterdiction() {
        when(interdictionPrecedentRepository.save(interdiction1)).thenReturn(interdiction1);

        InterdictionPrecedent result = interdictionPrecedentService.createInterdiction(interdiction1);

        assertNotNull(result);
        assertEquals(id1, result.getId());
        verify(interdictionPrecedentRepository, times(1)).save(interdiction1);
    }

    @Test
    void deleteInterdiction_shouldCallDeleteById() {
        interdictionPrecedentService.deleteInterdiction(id1);

        verify(interdictionPrecedentRepository, times(1)).deleteById(id1);
    }
}
