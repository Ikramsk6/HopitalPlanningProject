package service;

import com.example.HopitalPlanningProject.model.SequenceShift;
import com.example.HopitalPlanningProject.model.SequenceShiftId;
import com.example.HopitalPlanningProject.repositories.SequenceShiftRepository;
import com.example.HopitalPlanningProject.services.SequenceShiftService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class SequenceShiftServiceTest {

    @Mock
    private SequenceShiftRepository sequenceShiftRepository;

    @InjectMocks
    private SequenceShiftService sequenceShiftService;

    private SequenceShift sequenceShift;
    private SequenceShiftId sequenceShiftId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sequenceShiftId = new SequenceShiftId(1, 1); // ID fictif
        sequenceShift = new SequenceShift(sequenceShiftId);
        sequenceShift.setOrdre(1);
    }

    @Test
    void testGetSequenceShiftById() {
        when(sequenceShiftRepository.findById(sequenceShiftId)).thenReturn(Optional.of(sequenceShift));

        Optional<SequenceShift> result = sequenceShiftService.getSequenceShiftById(sequenceShiftId);

        assertTrue(result.isPresent());
        assertEquals(sequenceShift, result.get());
    }

    @Test
    void testSaveSequenceShift() {
        when(sequenceShiftRepository.save(sequenceShift)).thenReturn(sequenceShift);

        SequenceShift result = sequenceShiftService.saveSequenceShift(sequenceShift);

        assertNotNull(result);
        assertEquals(sequenceShift, result);
    }

    @Test
    void testDeleteSequenceShift() {
        doNothing().when(sequenceShiftRepository).deleteById(sequenceShiftId);

        sequenceShiftService.deleteSequenceShift(sequenceShiftId);

        verify(sequenceShiftRepository, times(1)).deleteById(sequenceShiftId);
    }
}
