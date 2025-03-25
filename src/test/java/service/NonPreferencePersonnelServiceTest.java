package service;

import com.example.HopitalPlanningProject.model.NonPreferencePersonnel;
import com.example.HopitalPlanningProject.model.NonPreferencePersonnelId;
import com.example.HopitalPlanningProject.repositories.NonPreferencePersonnelRepository;
import com.example.HopitalPlanningProject.services.NonPreferencePersonnelService;
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
public class NonPreferencePersonnelServiceTest {

    @Mock
    private NonPreferencePersonnelRepository nonPreferencePersonnelRepository;

    @InjectMocks
    private NonPreferencePersonnelService nonPreferencePersonnelService;

    private NonPreferencePersonnel nonPreferencePersonnel1;
    private NonPreferencePersonnel nonPreferencePersonnel2;
    private NonPreferencePersonnelId id1;
    private NonPreferencePersonnelId id2;

    @BeforeEach
    void setUp() {
        id1 = new NonPreferencePersonnelId(1, 1, 1);
        id2 = new NonPreferencePersonnelId(2, 2, 2);

        nonPreferencePersonnel1 = new NonPreferencePersonnel();
        nonPreferencePersonnel1.setId(id1);

        nonPreferencePersonnel2 = new NonPreferencePersonnel();
        nonPreferencePersonnel2.setId(id2);
    }

    @Test
    void getAllNonPreferencePersonnel_shouldReturnList() {
        when(nonPreferencePersonnelRepository.findAll()).thenReturn(Arrays.asList(nonPreferencePersonnel1, nonPreferencePersonnel2));

        List<NonPreferencePersonnel> result = nonPreferencePersonnelService.getAllNonPreferencePersonnel();

        assertEquals(2, result.size());
        verify(nonPreferencePersonnelRepository, times(1)).findAll();
    }

    @Test
    void getNonPreferencePersonnelById_shouldReturnNonPreferencePersonnelIfExists() {
        when(nonPreferencePersonnelRepository.findById(id1)).thenReturn(Optional.of(nonPreferencePersonnel1));

        Optional<NonPreferencePersonnel> result = nonPreferencePersonnelService.getNonPreferencePersonnelById(id1);

        assertTrue(result.isPresent());
        assertEquals(id1, result.get().getId());
        verify(nonPreferencePersonnelRepository, times(1)).findById(id1);
    }

    @Test
    void getNonPreferencePersonnelById_shouldReturnEmptyIfNotExists() {
        when(nonPreferencePersonnelRepository.findById(id2)).thenReturn(Optional.empty());

        Optional<NonPreferencePersonnel> result = nonPreferencePersonnelService.getNonPreferencePersonnelById(id2);

        assertFalse(result.isPresent());
        verify(nonPreferencePersonnelRepository, times(1)).findById(id2);
    }

    @Test
    void saveNonPreferencePersonnel_shouldSaveAndReturnNonPreferencePersonnel() {
        when(nonPreferencePersonnelRepository.save(nonPreferencePersonnel1)).thenReturn(nonPreferencePersonnel1);

        NonPreferencePersonnel result = nonPreferencePersonnelService.saveNonPreferencePersonnel(nonPreferencePersonnel1);

        assertNotNull(result);
        assertEquals(id1, result.getId());
        verify(nonPreferencePersonnelRepository, times(1)).save(nonPreferencePersonnel1);
    }

    @Test
    void deleteNonPreferencePersonnel_shouldCallDeleteById() {
        nonPreferencePersonnelService.deleteNonPreferencePersonnel(id1);

        verify(nonPreferencePersonnelRepository, times(1)).deleteById(id1);
    }
}
