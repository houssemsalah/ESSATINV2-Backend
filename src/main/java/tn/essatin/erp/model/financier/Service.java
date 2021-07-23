package tn.essatin.erp.model.financier;

import javax.persistence.*;

@Entity
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idService;
    private String designation;
    private String description;
    @ManyToOne
    private PrestataireService idPrestataire;

    public Service(String designation, String description, PrestataireService idPrestataire) {
        this.designation = designation;
        this.description = description;
        this.idPrestataire = idPrestataire;
    }

    public Service() {
    }

    public Integer getIdService() {
        return idService;
    }

    public void setIdService(Integer idService) {
        this.idService = idService;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PrestataireService getIdPrestataire() {
        return idPrestataire;
    }

    public void setIdPrestataire(PrestataireService idPrestataire) {
        this.idPrestataire = idPrestataire;
    }

    @Override
    public String toString() {
        return "Service{" +
                "idService=" + idService +
                ", designation='" + designation + '\'' +
                ", description='" + description + '\'' +
                ", idPrestataire=" + idPrestataire +
                '}';
    }
}
