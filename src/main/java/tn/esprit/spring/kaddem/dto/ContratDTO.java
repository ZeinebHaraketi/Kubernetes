package tn.esprit.spring.kaddem.dto;

import tn.esprit.spring.kaddem.entities.Specialite;

import java.util.Date;

public class ContratDTO {
     Integer idContrat;
     Date dateDebutContrat;
     Date dateFinContrat;
     Specialite specialite;
     Boolean archive;
     Integer montantContrat;

    public ContratDTO() {
    }

     public ContratDTO(Date dateDebutContrat, Date dateFinContrat, Specialite specialite, Boolean archive,
                      Integer montantContrat) {
        // super() is not needed, the compiler inserts it automatically
        this.dateDebutContrat = dateDebutContrat;
        this.dateFinContrat = dateFinContrat;
        this.specialite = specialite;
        this.archive = archive;
        this.montantContrat = montantContrat;
    }

    public ContratDTO(Integer idContrat, Date dateDebutContrat, Date dateFinContrat, Specialite specialite,
                      Boolean archive, Integer montantContrat) {
        // super() is not needed here either
        this.idContrat = idContrat;
        this.dateDebutContrat = dateDebutContrat;
        this.dateFinContrat = dateFinContrat;
        this.specialite = specialite;
        this.archive = archive;
        this.montantContrat = montantContrat;
    }
    public Integer getIdContrat() {
        return idContrat;
    }

    public void setIdContrat(Integer idContrat) {
        this.idContrat = idContrat;
    }

    public Date getDateDebutContrat() {
        return dateDebutContrat;
    }

    public void setDateDebutContrat(Date dateDebutContrat) {
        this.dateDebutContrat = dateDebutContrat;
    }

    public Date getDateFinContrat() {
        return dateFinContrat;
    }

    public void setDateFinContrat(Date dateFinContrat) {
        this.dateFinContrat = dateFinContrat;
    }

    public Specialite getSpecialite() {
        return specialite;
    }

    public void setSpecialite(Specialite specialite) {
        this.specialite = specialite;
    }

    public Boolean getArchive() {
        return archive;
    }

    public void setArchive(Boolean archive) {
        this.archive = archive;
    }

    public Integer getMontantContrat() {
        return montantContrat;
    }

    public void setMontantContrat(Integer montantContrat) {
        this.montantContrat = montantContrat;
    }
}