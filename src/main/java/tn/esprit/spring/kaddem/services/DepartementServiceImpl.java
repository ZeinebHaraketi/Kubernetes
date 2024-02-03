package tn.esprit.spring.kaddem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.dto.DepartementDTO;

import tn.esprit.spring.kaddem.repositories.DepartementRepository;

import java.util.List;

@Slf4j

@Service
public class DepartementServiceImpl implements IDepartementService{
	@Autowired
	DepartementRepository departementRepository;
	public List<Departement> retrieveAllDepartements(){
		return (List<Departement>) departementRepository.findAll();
	}

public DepartementDTO addDepartement( DepartementDTO departementDTO) {
    Departement departement = new Departement();
    departement.setNomDepart(departementDTO.getNomDepart());

    departement = departementRepository.save(departement);

    DepartementDTO responseDTO = new DepartementDTO();
    responseDTO.setIdDepart(departement.getIdDepart());
    responseDTO.setNomDepart(departement.getNomDepart());

    return responseDTO;
}


	public DepartementDTO updateDepartement( DepartementDTO departementDTO) {
    Departement departementToUpdate = departementRepository.findById(departementDTO.getIdDepart())
            .orElseThrow(() -> new EntityNotFoundException("Departement not found with ID: " + departementDTO.getIdDepart()));

    departementToUpdate.setNomDepart(departementDTO.getNomDepart());

    Departement updatedDepartement = departementRepository.save(departementToUpdate);

    DepartementDTO responseDTO = new DepartementDTO();
    responseDTO.setIdDepart(updatedDepartement.getIdDepart());
    responseDTO.setNomDepart(updatedDepartement.getNomDepart());

    return responseDTO;
}


	public Departement retrieveDepartement(Integer idDepart) {
    return departementRepository.findById(idDepart).orElse(null);
}

	public  void deleteDepartement(Integer idDepartement){
		Departement d=retrieveDepartement(idDepartement);
		departementRepository.delete(d);
	}



}