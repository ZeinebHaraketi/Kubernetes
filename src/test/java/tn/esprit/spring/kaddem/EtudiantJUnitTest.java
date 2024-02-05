package tn.esprit.spring.kaddem;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Option;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
 class EtudiantJUnitTest {

    @Autowired
    private EtudiantRepository etudiantRepository;


    @BeforeEach
   public  void setUp() {
        etudiantRepository.deleteAll();
    }

    @AfterEach
   public  void tearDown() {
        etudiantRepository.deleteAll();
    }

    @Test
     void saveEtudiant() {
        Etudiant etudiant = new Etudiant("Zayneb", "Haraketti");
        Etudiant savedEtudiant = etudiantRepository.save(etudiant);

        assertNotNull(savedEtudiant.getIdEtudiant());
        assertThat(savedEtudiant.getNomE()).isEqualTo(etudiant.getNomE());
    }

    @Test
     void findEtudiantById() {
        Etudiant etudiant = new Etudiant("Zeineb", "Haraketi");
        etudiant = etudiantRepository.save(etudiant);

        Etudiant foundEtudiant = etudiantRepository.findById(etudiant.getIdEtudiant()).orElse(null);

        assertNotNull(foundEtudiant);
        assertThat(foundEtudiant.getIdEtudiant()).isEqualTo(etudiant.getIdEtudiant());
    }

    @Test
     void updateEtudiant() {
        Etudiant etudiant = new Etudiant("Zeinebb", "Haraketii");
        etudiant = etudiantRepository.save(etudiant);

        Etudiant foundEtudiant = etudiantRepository.findById(etudiant.getIdEtudiant()).get();
        foundEtudiant.setNomE("Zaineb");
        etudiantRepository.save(foundEtudiant);

        Etudiant updatedEtudiant = etudiantRepository.findById(etudiant.getIdEtudiant()).get();
        assertThat(updatedEtudiant.getNomE()).isEqualTo("Zaineb");
    }

    @Test
     void deleteEtudiant() {
        Etudiant etudiant = new Etudiant("Zeineeb", "Harakati");
        etudiant = etudiantRepository.save(etudiant);

        etudiantRepository.deleteById(etudiant.getIdEtudiant());

        boolean exists = etudiantRepository.existsById(etudiant.getIdEtudiant());

        assertFalse(exists);
    }
}