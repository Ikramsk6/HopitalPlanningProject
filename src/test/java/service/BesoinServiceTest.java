package service;

import com.example.HopitalPlanningProject.model.Besoin;
import com.example.HopitalPlanningProject.model.BesoinId;
import com.example.HopitalPlanningProject.repositories.BesoinRepository;
import com.example.HopitalPlanningProject.services.BesoinService;
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
class BesoinServiceTest {

    @Mock
    private BesoinRepository besoinRepository;

    @InjectMocks
    private BesoinService besoinService;

    private BesoinId id1, id2;
    private Besoin besoin1, besoin2;

    @BeforeEach
    void setUp() {
        id1 = new BesoinId(1, 101);
        id2 = new BesoinId(2, 202);

        besoin1 = new Besoin();
        besoin1.setId(id1);
        besoin1.setValeurSouhaitee((short) 10);

        besoin2 = new Besoin();
        besoin2.setId(id2);
        besoin2.setValeurSouhaitee((short) 20);
    }

    @Test
    void getAllBesoins_shouldReturnListOfBesoins() {
        when(besoinRepository.findAll()).thenReturn(Arrays.asList(besoin1, besoin2));

        List<Besoin> besoins = besoinService.getAllBesoins();

        assertEquals(2, besoins.size());
        assertEquals(10, besoins.get(0).getValeurSouhaitee());
        assertEquals(20, besoins.get(1).getValeurSouhaitee());
        verify(besoinRepository, times(1)).findAll();
    }

    @Test
    void getBesoinById_shouldReturnBesoinIfExists() {
        when(besoinRepository.findById(id1)).thenReturn(Optional.of(besoin1));

        Optional<Besoin> foundBesoin = besoinService.getBesoinById(id1);

        assertTrue(foundBesoin.isPresent());
        assertEquals(10, foundBesoin.get().getValeurSouhaitee());
        verify(besoinRepository, times(1)).findById(id1);
    }

    @Test
    void getBesoinById_shouldReturnEmptyIfNotExists() {
        when(besoinRepository.findById(new BesoinId(99, 999))).thenReturn(Optional.empty());

        Optional<Besoin> foundBesoin = besoinService.getBesoinById(new BesoinId(99, 999));

        assertFalse(foundBesoin.isPresent());
        verify(besoinRepository, times(1)).findById(new BesoinId(99, 999));
    }

    @Test
    void saveBesoin_shouldSaveAndReturnBesoin() {
        when(besoinRepository.save(besoin1)).thenReturn(besoin1);

        Besoin savedBesoin = besoinService.saveBesoin(besoin1);

        assertNotNull(savedBesoin);
        assertEquals(10, savedBesoin.getValeurSouhaitee());
        verify(besoinRepository, times(1)).save(besoin1);
    }

    @Test
    void deleteBesoin_shouldCallDeleteById() {
        besoinService.deleteBesoin(id1);

        verify(besoinRepository, times(1)).deleteById(id1);
    }
}
