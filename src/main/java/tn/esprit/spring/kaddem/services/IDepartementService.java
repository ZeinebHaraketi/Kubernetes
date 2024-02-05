package tn.esprit.spring.kaddem.services;

import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.dto.DepartementDTO;


import java.util.List;

public interface IDepartementService {
    public List<Departement> retrieveAllDepartements();

    public DepartementDTO addDepartement(DepartementDTO dDTO);

    public  Departement retrieveDepartement (Integer idDepart);

    public  void deleteDepartement(Integer idDepartement);

}
