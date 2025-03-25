package service;

import com.example.HopitalPlanningProject.model.PreferencePersonnel;
import com.example.HopitalPlanningProject.model.PreferencePersonnelId;
import com.example.HopitalPlanningProject.repositories.PreferencePersonnelRepository;
import com.example.HopitalPlanningProject.services.PreferencePersonnelService;
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
public class PreferencePersonnelServiceTest {

    @Mock
    private PreferencePersonnelRepository preferencePersonnelRepository;

    @InjectMocks
    private PreferencePersonnelService preferencePersonnelService;

    private PreferencePersonnel preferencePersonnel1;
    private PreferencePersonnel preferencePersonnel2;
    private PreferencePersonnelId id1;
    private PreferencePersonnelId id2;

    @BeforeEach
    void setUp() {
        id1 = new PreferencePersonnelId(1, 2, 3);  // Remplace ces valeurs par celles de ton modèle.
        id2 = new PreferencePersonnelId(2, 3, 4);  // Remplace ces valeurs par celles de ton modèle.

        preferencePersonnel1 = new PreferencePersonnel();
        preferencePersonnel1.setId(id1);

        preferencePersonnel2 = new PreferencePersonnel();
        preferencePersonnel2.setId(id2);
    }

    @Test
    void getAllPreferencePersonnel_shouldReturnList() {
        when(preferencePersonnelRepository.findAll()).thenReturn(Arrays.asList(preferencePersonnel1, preferencePersonnel2));

        List<PreferencePersonnel> result = preferencePersonnelService.getAllPreferencePersonnel();

        assertEquals(2, result.size());
        verify(preferencePersonnelRepository, times(1)).findAll();
    }

    @Test
    void getPreferencePersonnelById_shouldReturnPreferencePersonnelIfExists() {
        when(preferencePersonnelRepository.findById(id1)).thenReturn(Optional.of(preferencePersonnel1));

        Optional<PreferencePersonnel> result = preferencePersonnelService.getPreferencePersonnelById(id1);

        assertTrue(result.isPresent());
        assertEquals(id1, result.get().getId());
        verify(preferencePersonnelRepository, times(1)).findById(id1);
    }

    @Test
    void getPreferencePersonnelById_shouldReturnEmptyIfNotExists() {
        when(preferencePersonnelRepository.findById(id1)).thenReturn(Optional.empty());

        Optional<PreferencePersonnel> result = preferencePersonnelService.getPreferencePersonnelById(id1);

        assertFalse(result.isPresent());
        verify(preferencePersonnelRepository, times(1)).findById(id1);
    }

    @Test
    void savePreferencePersonnel_shouldSaveAndReturnPreferencePersonnel() {
        when(preferencePersonnelRepository.save(preferencePersonnel1)).thenReturn(preferencePersonnel1);

        PreferencePersonnel result = preferencePersonnelService.savePreferencePersonnel(preferencePersonnel1);

        assertNotNull(result);
        assertEquals(id1, result.getId());
        verify(preferencePersonnelRepository, times(1)).save(preferencePersonnel1);
    }

    @Test
    void deletePreferencePersonnel_shouldCallDeleteById() {
        preferencePersonnelService.deletePreferencePersonnel(id1);

        verify(preferencePersonnelRepository, times(1)).deleteById(id1);
    }
}
