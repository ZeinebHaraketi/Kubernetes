package tn.esprit.spring.kaddem.dto;

public class UniversiteDTO {
    private Integer idUniv;

    private String nomUniv;

    public UniversiteDTO() {
        super();
    }

    public UniversiteDTO(String nomUniv) {
        super();
        this.nomUniv = nomUniv;
    }

    public Integer getIdUniv() {
        return idUniv;
    }

    public void setIdUniv(Integer idUniv) {
        this.idUniv = idUniv;
    }

    public String getNomUniv() {
        return nomUniv;
    }

    public void setNomUniv(String nomUniv) {
        this.nomUniv = nomUniv;
    }
}