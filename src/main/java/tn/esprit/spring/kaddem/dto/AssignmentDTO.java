package tn.esprit.spring.kaddem.dto;

public class AssignmentDTO {
     Integer idContrat;
     Integer idEquipe;

    public AssignmentDTO() {
        super();
    }

    public AssignmentDTO(Integer idContrat, Integer idEquipe) {
        super();
        this.idContrat = idContrat;
        this.idEquipe = idEquipe;
    }


}