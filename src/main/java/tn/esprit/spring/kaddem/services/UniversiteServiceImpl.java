package tn.esprit.spring.kaddem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.UniversiteRepository;

import java.util.List;
import java.util.Set;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import java.util.HashSet;



@Service
public class UniversiteServiceImpl implements IUniversiteService{
@Autowired
    UniversiteRepository universiteRepository;
@Autowired
    DepartementRepository departementRepository;
    public UniversiteServiceImpl() {
            // Default constructor required for certain frameworks or instantiation scenarios
    }
  public   List<Universite> retrieveAllUniversites(){
return (List<Universite>) universiteRepository.findAll();
    }

 public    Universite addUniversite (Universite  u){
return  (universiteRepository.save(u));
    }

 public    Universite updateUniversite (Universite  u){
     return  (universiteRepository.save(u));
    }

 public Universite retrieveUniversite(Integer idUniversite){
    Optional<Universite> optionalUniversite = universiteRepository.findById(idUniversite);
    if (optionalUniversite.isPresent()) {
        return optionalUniversite.get();
    } else {
        return null; 
    }
}

    public  void deleteUniversite(Integer idUniversite){
        universiteRepository.delete(retrieveUniversite(idUniversite));
    }

   public void assignUniversiteToDepartement(Integer idUniversite, Integer idDepartement){
    Universite u = universiteRepository.findById(idUniversite).orElse(null);
    Departement d = departementRepository.findById(idDepartement).orElse(null);

    if (u != null && d != null) {
        u.getDepartements().add(d);
        universiteRepository.save(u);
    } else {
        throw new EntityNotFoundException("Universite or Departement not found for given ids");
    }
}

public Set<Departement> retrieveDepartementsByUniversite(Integer idUniversite) {
    Universite u = universiteRepository.findById(idUniversite).orElse(null);

    if (u != null) {
        return u.getDepartements();
    } else {
        return new HashSet<>();
    }
}

}