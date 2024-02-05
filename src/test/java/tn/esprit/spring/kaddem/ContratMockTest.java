package tn.esprit.spring.kaddem;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Specialite;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.services.ContratServiceImpl;

import java.util.Date;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ContratMockTest {

    @Mock
    private ContratRepository contratRepository;

    @InjectMocks
    private ContratServiceImpl contratService;

    private Contrat contrat;
    @BeforeEach
    public void setup() {
        Date dateDebut = new Date();
        Date dateFin = new Date();
        contrat = new Contrat(dateDebut, dateFin, Specialite.IA, false, 1000);
    }
    @Test
    public void testCreateContrat() {
        when(contratRepository.save(any(Contrat.class))).thenReturn(contrat);

        Contrat created = contratService.addContrat(contrat);

        assertNotNull(created);
        assertEquals(Specialite.IA, created.getSpecialite());
    }
    @Test
    public void testGetContratById() {
        when(contratRepository.findById(1)).thenReturn(Optional.of(contrat));

        Contrat found = contratService.retrieveContrat(1);

        assertNotNull(found);
        assertEquals(1000, found.getMontantContrat().intValue());
    }

   /*@Test
public void testUpdateContrat() {
    Integer contratId = 1;
    Contrat originalContrat = new Contrat(); // Assuming a constructor or setters to initialize
    originalContrat.setIdContrat(contratId);
    
    Contrat updatedContrat = new Contrat(); // Similarly initialize
    updatedContrat.setIdContrat(contratId);
    updatedContrat.setMontantContrat(1500); // Assume this is the updated field

    when(contratRepository.findById(contratId)).thenReturn(Optional.of(originalContrat));
    when(contratRepository.save(any(Contrat.class))).thenReturn(updatedContrat);

    Contrat result = contratService.updateContrat(originalContrat);

    assertNotNull(result);
    assertEquals(Integer.valueOf(1500), result.getMontantContrat());
    verify(contratRepository).save(any(Contrat.class)); // Verify save was called
}*/



  @Test
public void testDeleteContrat() {
    Integer contratId = 1;
    Contrat contratD = new Contrat();
    contratD.setIdContrat(contratId); // Ensure the contract has an ID.

    when(contratRepository.findById(contratId)).thenReturn(Optional.of(contratD));

    contratService.removeContrat(contratId);

    verify(contratRepository).delete(contratD); // Adjust this line to match your service's actual behavior.
}



}
