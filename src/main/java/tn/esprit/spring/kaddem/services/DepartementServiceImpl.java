package tn.esprit.spring.kaddem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;

import java.util.List;
import tn.esprit.spring.kaddem.dto.DepartementDTO;


@Slf4j

@Service
public class DepartementServiceImpl implements IDepartementService{
	@Autowired
	DepartementRepository departementRepository;
	public List<Departement> retrieveAllDepartements(){
		return (List<Departement>) departementRepository.findAll();
	}

	@Override
    public DepartementDTO addDepartement(DepartementDTO dDTO) {
        Departement d = convertToEntity(dDTO);
        d = departementRepository.save(d);
        return convertToDto(d);
    }

    DepartementDTO updateDepartement(DepartementDTO dDTO){
		Departement d = convertToEntity(dDTO);
        d = departementRepository.save(d);
        return convertToDto(d);	
	}

	public Departement retrieveDepartement(Integer idDepart) {
    return departementRepository.findById(idDepart).orElse(null);
	}

	public  void deleteDepartement(Integer idDepartement){
		Departement d=retrieveDepartement(idDepartement);
		departementRepository.delete(d);
	}


private Departement convertToEntity(DepartementDTO dDTO) {
    Departement departement = new Departement();
    departement.setNomDepart(dDTO.getNomDepart());
    return departement;
}

private DepartementDTO convertToDto(Departement d) {
    DepartementDTO dDTO = new DepartementDTO();
    dDTO.setNomDepart(d.getNomDepart());
    return dDTO;
}


}
