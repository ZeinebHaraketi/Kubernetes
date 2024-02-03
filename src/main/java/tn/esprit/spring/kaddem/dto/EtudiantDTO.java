package tn.esprit.spring.kaddem.dto;

import tn.esprit.spring.kaddem.entities.Option;

public class EtudiantDTO {
    private Integer idEtudiant;
    private String nomE;
    private String prenomE;
    private Option op;

    // Constructors, getters, and setters

    public EtudiantDTO() {
    }

    public EtudiantDTO(Integer idEtudiant, String nomE, String prenomE, Option op) {
        this.idEtudiant = idEtudiant;
        this.nomE = nomE;
        this.prenomE = prenomE;
        this.op = op;
    }

    // Getters and setters
    public Integer getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(Integer idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    public String getNomE() {
        return nomE;
    }

    public void setNomE(String nomE) {
        this.nomE = nomE;
    }

    public String getPrenomE() {
        return prenomE;
    }

    public void setPrenomE(String prenomE) {
        this.prenomE = prenomE;
    }

    public Option getOp() {
        return op;
    }

    public void setOp(Option op) {
        this.op = op;
    }
}