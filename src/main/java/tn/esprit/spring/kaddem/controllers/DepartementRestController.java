package tn.esprit.spring.kaddem.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.services.IDepartementService;
import tn.esprit.spring.kaddem.dto.DepartementDTO;


import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/departement")
public class DepartementRestController {
	IDepartementService departementService;

	@GetMapping("/retrieve-all-departements")
	public List<Departement> getDepartements() {
    	return departementService.retrieveAllDepartements();
	}


	@GetMapping("/retrieve-departement/{departement-id}")
	public Departement retrieveDepartement(@PathVariable("departement-id") Integer departementId) {
		return departementService.retrieveDepartement(departementId);
	}

	@PostMapping("/add-departement")
	public DepartementDTO addDepartement(@RequestBody DepartementDTO departementDTO) {
        return departementService.addDepartement(departementDTO);
    }


	@DeleteMapping("/remove-departement/{departement-id}")
	public void removeDepartement(@PathVariable("departement-id") Integer departementId) {
		departementService.deleteDepartement(departementId);
	}

	@PutMapping("/update-departement")
	public Departement updateDepartement(@RequestBody Departement e) {
    	return departementService.updateDepartement(e);
	}

}


