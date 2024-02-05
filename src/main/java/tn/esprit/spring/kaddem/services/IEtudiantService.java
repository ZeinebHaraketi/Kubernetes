package tn.esprit.spring.kaddem.services;

import tn.esprit.spring.kaddem.entities.Etudiant;

import java.util.List;

import tn.esprit.spring.kaddem.dto.EtudiantDTO;


public interface IEtudiantService {
    public List<Etudiant> retrieveAllEtudiants();

    public EtudiantDTO addEtudiant(EtudiantDTO etudiantDTO);

    public EtudiantDTO updateEtudiant(EtudiantDTO etudiantDTO);

    public Etudiant retrieveEtudiant(Integer  idEtudiant);

    public void removeEtudiant(Integer idEtudiant);

    public void assignEtudiantToDepartement (Integer etudiantId, Integer departementId);

    public Etudiant addAndAssignEtudiantToEquipeAndContract(Etudiant e, Integer idContrat, Integer idEquipe);

    public 	List<Etudiant> getEtudiantsByDepartement (Integer idDepartement);
}
