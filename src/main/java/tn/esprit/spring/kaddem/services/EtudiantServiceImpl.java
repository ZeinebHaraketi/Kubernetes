package tn.esprit.spring.kaddem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import tn.esprit.spring.kaddem.dto.EtudiantDTO;
import javax.persistence.EntityNotFoundException;



@Service
@Slf4j
public class EtudiantServiceImpl implements IEtudiantService{
	@Autowired
	EtudiantRepository etudiantRepository ;
	@Autowired
	ContratRepository contratRepository;
	@Autowired
	EquipeRepository equipeRepository;
    @Autowired
    DepartementRepository departementRepository;
	public List<Etudiant> retrieveAllEtudiants(){
	return (List<Etudiant>) etudiantRepository.findAll();
	}

	public EtudiantDTO addEtudiant(EtudiantDTO etudiantDTO){
        Etudiant etudiant = convertToEntity(etudiantDTO);
        etudiant = etudiantRepository.save(etudiant);
        return convertToDTO(etudiant);
	}

	public EtudiantDTO updateEtudiant(EtudiantDTO etudiantDTO) {
        // Assurez-vous que l'étudiant existe avant de tenter de le mettre à jour
        Etudiant etudiantToUpdate = etudiantRepository.findById(etudiantDTO.getIdEtudiant())
                .orElseThrow(() -> new EntityNotFoundException("Etudiant not found with ID: " + etudiantDTO.getIdEtudiant()));

        // Mise à jour des champs de l'entité
        etudiantToUpdate.setNomE(etudiantDTO.getNomE());
        etudiantToUpdate.setPrenomE(etudiantDTO.getPrenomE());
		etudiantToUpdate.setOp(etudiantDTO.getOp());

        // Ajoutez ici la mise à jour des autres champs selon besoin

        Etudiant updatedEtudiant = etudiantRepository.save(etudiantToUpdate);

        // Convertir en DTO pour le retour
        return convertToDTO(updatedEtudiant);
    }

	public Etudiant retrieveEtudiant(Integer idEtudiant) {
		Optional<Etudiant> etudiantOptional = etudiantRepository.findById(idEtudiant);

		if (etudiantOptional.isPresent()) {
			return etudiantOptional.get();
		} else {
			return null; 
		}
	}

	public void removeEtudiant(Integer idEtudiant){
	Etudiant e=retrieveEtudiant(idEtudiant);
	etudiantRepository.delete(e);
	}

	public void assignEtudiantToDepartement(Integer etudiantId, Integer departementId) {
		Optional<Etudiant> etudiantOptional = etudiantRepository.findById(etudiantId);
		Optional<Departement> departementOptional = departementRepository.findById(departementId);

		if (etudiantOptional.isPresent() && departementOptional.isPresent()) {
			Etudiant etudiant = etudiantOptional.get();
			Departement departement = departementOptional.get();
			etudiant.setDepartement(departement);
			etudiantRepository.save(etudiant);
		} else {
			// For example, you can log an error message or notify the user.
		}
	}

	@Transactional
	public Etudiant addAndAssignEtudiantToEquipeAndContract(Etudiant e, Integer idContrat, Integer idEquipe) {
		Contrat c = contratRepository.findById(idContrat).orElse(null);
		Equipe eq = equipeRepository.findById(idEquipe).orElse(null);

		if (c != null && eq != null) {
			c.setEtudiant(e);
			eq.getEtudiants().add(e);
			return e;
		} else {
			return null; // Or return some other value indicating failure.
		}
	}

	public 	List<Etudiant> getEtudiantsByDepartement (Integer idDepartement){
		return  etudiantRepository.findEtudiantsByDepartementIdDepart((idDepartement));
	}

	private Etudiant convertToEntity(EtudiantDTO etudiantDTO) {
        Etudiant etudiant = new Etudiant();
        etudiant.setNomE(etudiantDTO.getNomE());
        etudiant.setPrenomE(etudiantDTO.getPrenomE());
		etudiant.setOp(etudiantDTO.getOp());

        // Autres conversions de champs
        return etudiant;
    }

    // Méthode de conversion Etudiant -> EtudiantDTO
    private EtudiantDTO convertToDTO(Etudiant etudiant) {
        EtudiantDTO etudiantDTO = new EtudiantDTO();
        etudiantDTO.setIdEtudiant(etudiant.getIdEtudiant());
        etudiantDTO.setNomE(etudiant.getNomE());
        etudiantDTO.setPrenomE(etudiant.getPrenomE());
		etudiantDTO.setOp(etudiant.getOp());

        // Autres conversions de champs
        return etudiantDTO;
    }

}
