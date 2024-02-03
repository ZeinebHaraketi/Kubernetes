package tn.esprit.spring.kaddem.controllers;

import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.kaddem.dto.ContratDTO;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.services.IContratService;

import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/contrat")
public class ContratRestController {

	IContratService contratService;


	@PostMapping("/add-contrat")
	public ContratDTO addContrat(@RequestBody ContratDTO contratDTO) {
		Contrat contrat = convertToContrat(contratDTO);
		contrat = contratService.addContrat(contrat);
		return convertToContratDTO(contrat);
	}

	private Contrat convertToContrat(ContratDTO contratDTO) {
		Contrat contrat = new Contrat();
		contrat.setIdContrat(contratDTO.getIdContrat());
		contrat.setDateDebutContrat(contratDTO.getDateDebutContrat());
		contrat.setDateFinContrat(contratDTO.getDateFinContrat());
		contrat.setSpecialite(contratDTO.getSpecialite());
		contrat.setArchive(contratDTO.getArchive());
		contrat.setMontantContrat(contratDTO.getMontantContrat());


		return contrat;
	}

	private ContratDTO convertToContratDTO(Contrat contrat) {
		ContratDTO contratDTO = new ContratDTO();
		contratDTO.setIdContrat(contrat.getIdContrat());
		contratDTO.setDateDebutContrat(contrat.getDateDebutContrat());
		contratDTO.setDateFinContrat(contrat.getDateFinContrat());
		contratDTO.setSpecialite(contrat.getSpecialite());
		contratDTO.setArchive(contrat.isArchive());
		contratDTO.setMontantContrat(contrat.getMontantContrat());


		return contratDTO;
	}



	@GetMapping("/retrieve-all-contrats")
	public List<Contrat> getContrats() {
		return contratService.retrieveAllContrats();

	}
	@GetMapping("/retrieve-contrat/{contrat-id}")
	public Contrat retrieveContrat(@PathVariable("contrat-id") Integer contratId) {
		return contratService.retrieveContrat(contratId);
	}



	@DeleteMapping("/remove-contrat/{contrat-id}")
	public void removeContrat(@PathVariable("contrat-id") Integer contratId) {
		contratService.removeContrat(contratId);
	}




	@PutMapping("/update-contrat")
	public ContratDTO updateContrat(@RequestBody ContratDTO contratDTO) {
		Contrat updatedContrat = contratService.updateContrat(contratDTO);
		return convertToContratDTO(updatedContrat);
	}


	@PutMapping(value = "/assignContratToEtudiant/{idContrat}/{nomE}/{prenomE}")
	public Contrat assignContratToEtudiant (Integer idContrat, String nomE, String prenomE){
		return 	(contratService.affectContratToEtudiant(idContrat, nomE, prenomE));
	}

		@GetMapping(value = "/getnbContratsValides/{startDate}/{endDate}")
		public Integer getnbContratsValides(@PathVariable(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
										  @PathVariable(name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {

			return contratService.nbContratsValides(startDate, endDate);
		}

    @Scheduled(cron="0 0 13 * * *")//(cron="0 0 13 * * ?")(fixedRate =21600)
	@PutMapping(value = "/majStatusContrat")
	public void majStatusContrat (){
		contratService.retrieveAndUpdateStatusContrat();

	}


	@GetMapping("/calculChiffreAffaireEntreDeuxDate/{startDate}/{endDate}")
	@ResponseBody
	public float calculChiffreAffaireEntreDeuxDates(@PathVariable(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
	@PathVariable(name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {

		return contratService.getChiffreAffaireEntreDeuxDates(startDate, endDate);
	}
}

