package tn.esprit.spring.kaddem;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import java.util.Arrays;

import org.junit.jupiter.api.*;

import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Specialite;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;
import tn.esprit.spring.kaddem.services.ContratServiceImpl;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@Configuration

class ContratServiceImplIntegrationTest {

    @Mock
    private ContratRepository contratRepository;
    @Mock
    private EtudiantRepository etudiantRepository;
    @InjectMocks
    private ContratServiceImpl contratService;

    @Test

    void testConstructorWithValidValues() {
        Date dateDebutContrat = new Date();
        Date dateFinContrat = new Date();
        Specialite specialite = Specialite.IA;
        boolean archive = false;
        int montantContrat = 1000;

        Contrat instance = new Contrat(dateDebutContrat, dateFinContrat, specialite, archive, montantContrat);

        assertNotNull(instance);
        assertEquals(dateDebutContrat, instance.getDateDebutContrat());
        assertEquals(dateFinContrat, instance.getDateFinContrat());
        assertEquals(specialite, instance.getSpecialite());
        assertEquals(archive, instance.getArchive());
        assertEquals(montantContrat, instance.getMontantContrat());
    }
    @Test
    void testAddContrat() {
        Contrat contratToAdd = new Contrat();
        contratToAdd.setDateDebutContrat(new Date());
        contratToAdd.setDateFinContrat(new Date());
        contratToAdd.setSpecialite(Specialite.IA);
        contratToAdd.setArchive(false);
        contratToAdd.setMontantContrat(1000);

        when(contratRepository.save(Mockito.any(Contrat.class))).thenReturn(contratToAdd);

        Contrat addedContrat = contratService.addContrat(contratToAdd);

        assertNull(addedContrat.getIdContrat());
        assertEquals(contratToAdd.getDateDebutContrat(), addedContrat.getDateDebutContrat());
        assertEquals(contratToAdd.getDateFinContrat(), addedContrat.getDateFinContrat());
        assertEquals(contratToAdd.getSpecialite(), addedContrat.getSpecialite());
        assertEquals(contratToAdd.getArchive(), addedContrat.getArchive());
        assertEquals(contratToAdd.getMontantContrat(), addedContrat.getMontantContrat());
    }

    @Test
    void testUpdateContrat() {
        Contrat contratToUpdate = new Contrat();
        contratToUpdate.setIdContrat(1); // Set a valid ID
        contratToUpdate.setDateDebutContrat(new Date());
        contratToUpdate.setDateFinContrat(new Date());
        contratToUpdate.setSpecialite(Specialite.CLOUD);
        contratToUpdate.setArchive(true);
        contratToUpdate.setMontantContrat(1500);

        when(contratRepository.findById(1)).thenReturn(Optional.of(contratToUpdate));
        when(contratRepository.save(Mockito.any(Contrat.class))).thenReturn(contratToUpdate);

        Contrat updatedContrat = contratService.updateContrat(contratToUpdate);

        assertNotNull(updatedContrat);
        assertEquals(Specialite.CLOUD, updatedContrat.getSpecialite());
    }

    @Test
    void testRetrieveAllContrats() {
        when(contratRepository.findAll()).thenReturn(Arrays.asList(new Contrat(), new Contrat())); // Mock data
        List<Contrat> result = contratService.retrieveAllContrats();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testRetrieveContrat() {
        Contrat contrat = new Contrat();
        contrat.setIdContrat(6); // Set a valid ID
        when(contratRepository.findById(6)).thenReturn(Optional.of(contrat));

        Contrat result = contratService.retrieveContrat(6);

        assertNotNull(result);
    }

    @Test
    void testRemoveContrat() {
        Contrat contrat = new Contrat();
        contrat.setIdContrat(6); // Set a valid ID

        when(contratRepository.findById(6)).thenReturn(Optional.of(contrat));

        contratService.removeContrat(6);

        assertNull(contratService.retrieveContrat(8));
    }





}
