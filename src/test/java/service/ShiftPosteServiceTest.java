package service;

import com.example.HopitalPlanningProject.model.ShiftPoste;
import com.example.HopitalPlanningProject.repositories.ShiftPosteRepository;
import com.example.HopitalPlanningProject.services.ShiftPosteService;
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
public class ShiftPosteServiceTest {

    @Mock
    private ShiftPosteRepository shiftPosteRepository;

    @InjectMocks
    private ShiftPosteService shiftPosteService;

    private ShiftPoste shiftPoste1;
    private ShiftPoste shiftPoste2;

    @BeforeEach
    void setUp() {
        shiftPoste1 = new ShiftPoste(1, true, "MOR", "Jour", "Médecin");
        shiftPoste2 = new ShiftPoste(2, false, "NOC", "Nuit", "Infirmier");
    }

    @Test
    void getAllShiftPostes_shouldReturnList() {
        when(shiftPosteRepository.findAll()).thenReturn(Arrays.asList(shiftPoste1, shiftPoste2));

        List<ShiftPoste> result = shiftPosteService.getAllShiftPostes();

        assertEquals(2, result.size());
        verify(shiftPosteRepository, times(1)).findAll();
    }

    @Test
    void getShiftPosteById_shouldReturnShiftPosteIfExists() {
        when(shiftPosteRepository.findById(1)).thenReturn(Optional.of(shiftPoste1));

        Optional<ShiftPoste> result = shiftPosteService.getShiftPosteById(1);

        assertTrue(result.isPresent());
        assertEquals(shiftPoste1.getIdShift(), result.get().getIdShift());
        verify(shiftPosteRepository, times(1)).findById(1);
    }

    @Test
    void getShiftPosteById_shouldReturnEmptyIfNotExists() {
        when(shiftPosteRepository.findById(1)).thenReturn(Optional.empty());

        Optional<ShiftPoste> result = shiftPosteService.getShiftPosteById(1);

        assertFalse(result.isPresent());
        verify(shiftPosteRepository, times(1)).findById(1);
    }

    @Test
    void saveShiftPoste_shouldSaveAndReturnShiftPoste() {
        when(shiftPosteRepository.save(shiftPoste1)).thenReturn(shiftPoste1);

        ShiftPoste result = shiftPosteService.saveShiftPoste(shiftPoste1);

        assertNotNull(result);
        assertEquals(shiftPoste1.getIdShift(), result.getIdShift());
        verify(shiftPosteRepository, times(1)).save(shiftPoste1);
    }

    @Test
    void deleteShiftPoste_shouldCallDeleteById() {
        shiftPosteService.deleteShiftPoste(1);

        verify(shiftPosteRepository, times(1)).deleteById(1);
    }
}
