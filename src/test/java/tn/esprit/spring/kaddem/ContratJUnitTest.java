package tn.esprit.spring.kaddem;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Etudiant;

import tn.esprit.spring.kaddem.entities.Specialite;
import tn.esprit.spring.kaddem.repositories.ContratRepository;

import java.util.Date;


@SpringBootTest
@ActiveProfiles("test")
 class ContratJUnitTest {

    @Autowired
    private ContratRepository contratRepository;

    @BeforeEach
    public void setUp() {
        contratRepository.deleteAll();
    }

    @AfterEach
    public void tearDown() {
        contratRepository.deleteAll();
    }

    @Test
     void saveContrat() {
        Date dateDebut = new Date();
        Date dateFin = new Date();
        Contrat contrat = new Contrat(dateDebut, dateFin, Specialite.IA, false, 1000);
        Contrat savedContrat = contratRepository.save(contrat);

        assertNotNull(savedContrat.getIdContrat());
        assertThat(savedContrat.getMontantContrat()).isEqualTo(1000);
    }

    @Test
     void findContratById() {
        Date dateDebut = new Date();
        Date dateFin = new Date();
        Contrat contrat = new Contrat(dateDebut, dateFin, Specialite.IA, false, 1000);
        contrat = contratRepository.save(contrat);

        Contrat foundContrat = contratRepository.findById(contrat.getIdContrat()).orElse(null);

        assertNotNull(foundContrat);
        assertThat(foundContrat.getIdContrat()).isEqualTo(contrat.getIdContrat());
    }

    @Test
     void updateContrat() {
        Date dateDebut = new Date();
        Date dateFin = new Date();
        Contrat contrat = new Contrat(dateDebut, dateFin, Specialite.IA, false, 1000);
        contrat = contratRepository.save(contrat);

        Contrat foundContrat = contratRepository.findById(contrat.getIdContrat()).get();
        foundContrat.setMontantContrat(1500);
        contratRepository.save(foundContrat);

        Contrat updatedContrat = contratRepository.findById(contrat.getIdContrat()).get();
        assertThat(updatedContrat.getMontantContrat()).isEqualTo(1500);
    }

    @Test
     void deleteContrat() {
        Date dateDebut = new Date();
        Date dateFin = new Date();
        Contrat contrat = new Contrat(dateDebut, dateFin, Specialite.IA, false, 1000);
        contrat = contratRepository.save(contrat);

        contratRepository.deleteById(contrat.getIdContrat());

        boolean exists = contratRepository.existsById(contrat.getIdContrat());

        assertFalse(exists);
    }
}