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

    @Test
public void testUpdateContrat() {
    Integer contratId = 1;
    Contrat contratToUpdate = new Contrat();
    contratToUpdate.setIdContrat(contratId);
    contratToUpdate.setMontantContrat(1000); // Valeur initiale

    when(contratRepository.findById(contratId)).thenReturn(Optional.of(contratToUpdate));
    when(contratRepository.save(contratToUpdate)).thenReturn(contratToUpdate); // Simulez le comportement de sauvegarde

    contratToUpdate.setMontantContrat(1500); // Mise à jour du montant
    Contrat updatedContrat = contratService.updateContrat(contratToUpdate); // Assumez que cette méthode fait ce qu'elle est censée faire.

    assertNotNull(updatedContrat);
    assertEquals(Integer.valueOf(1500), updatedContrat.getMontantContrat());
}


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
