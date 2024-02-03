package tn.esprit.spring.kaddem.dto;

import tn.esprit.spring.kaddem.entities.Niveau;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EquipeDTO {
    private Integer idEquipe;
    private String nomEquipe;
    private Niveau niveau;



}