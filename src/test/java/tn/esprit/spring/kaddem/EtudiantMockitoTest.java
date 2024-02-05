package tn.esprit.spring.kaddem;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Option;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;
import tn.esprit.spring.kaddem.services.EtudiantServiceImpl;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
class EtudiantMockitoTest {

    @Mock
    private EtudiantRepository etudiantRepository;

    @InjectMocks
    private EtudiantServiceImpl etudiantService;

    private Etudiant etudiant;

    @Before
    public void setup() {
        etudiant = new Etudiant(1, "Doe", "John");
    }

    @Test
   public  void testCreateEtudiant() {
        when(etudiantRepository.save(any(Etudiant.class))).thenReturn(etudiant);

        Etudiant created = etudiantService.addEtudiant(etudiant);

        assertNotNull(created);
        assertEquals("Doe", created.getNomE());
    }

    @Test
   public  void testGetEtudiantById() {
        when(etudiantRepository.findById(1)).thenReturn(Optional.of(etudiant));

        Etudiant found = etudiantService.retrieveEtudiant(1);

        assertNotNull(found);
        assertEquals("John", found.getPrenomE());
    }


}