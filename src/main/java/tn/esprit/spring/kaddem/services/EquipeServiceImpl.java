package tn.esprit.spring.kaddem.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Niveau;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Slf4j
@AllArgsConstructor
@Service
public class EquipeServiceImpl implements IEquipeService{
	EquipeRepository equipeRepository;


	public List<Equipe> retrieveAllEquipes(){
	return  (List<Equipe>) equipeRepository.findAll();
	}
	public Equipe addEquipe(Equipe e){
		return (equipeRepository.save(e));
	}

	public  void deleteEquipe(Integer idEquipe){
		Equipe e=retrieveEquipe(idEquipe);
		equipeRepository.delete(e);
	}

	public Equipe retrieveEquipe(Integer equipeId){
		return equipeRepository.findById(equipeId).get();
	}

	public Equipe updateEquipe(Equipe e){
	return (	equipeRepository.save(e));
	}

public void evoluerEquipes() {
	List<Equipe> equipes = (List<Equipe>) equipeRepository.findAll();

	for (Equipe equipe : equipes) {
		if (shouldUpgradeEquipe(equipe)) {
			upgradeEquipe(equipe);
		}
	}
}	


private void upgradeEquipe(Equipe equipe) {
		if (equipe.getNiveau() == Niveau.JUNIOR) {
			equipe.setNiveau(Niveau.SENIOR);
		} else if (equipe.getNiveau() == Niveau.SENIOR) {
			equipe.setNiveau(Niveau.EXPERT);
		}

		equipeRepository.save(equipe);
	}

	private boolean shouldUpgradeEquipe(Equipe equipe) {
		if ((equipe.getNiveau() == Niveau.JUNIOR || equipe.getNiveau() == Niveau.SENIOR)) {
			List<Etudiant> etudiants = (List<Etudiant>) equipe.getEtudiants();
			int nbEtudiantsAvecContratsActifs = countActiveContracts(etudiants);

			return nbEtudiantsAvecContratsActifs >= 3;
		}
		return false;
	}
}