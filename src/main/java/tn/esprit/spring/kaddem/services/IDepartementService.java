package tn.esprit.spring.kaddem.services;

import tn.esprit.spring.kaddem.entities.Departement;

import java.util.List;

public interface IDepartementService {
    public List<Departement> retrieveAllDepartements();

    public DepartementDTO addDepartement(@RequestBody DepartementDTO departementDTO);
	public DepartementDTO updateDepartement(@RequestBody DepartementDTO departementDTO);
    
    public  Departement retrieveDepartement (Integer idDepart);

    public  void deleteDepartement(Integer idDepartement);

}
