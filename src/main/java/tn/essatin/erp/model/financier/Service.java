package tn.essatin.erp.model.financier;

import javax.persistence.*;

@Entity
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String designation;
    private String description;
    @OneToOne
    private PrestataireService Prestataire;

    public Service(String designation, String description, PrestataireService Prestataire) {
        this.designation = designation;
        this.description = description;
        this.Prestataire = Prestataire;
    }

    public Service() {
    }

    public Integer getIdService() {
        return id;
    }

    public void setIdService(Integer id) {
        this.id = id;
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
        return Prestataire;
    }

    public void setIdPrestataire(PrestataireService Prestataire) {
        this.Prestataire = Prestataire;
    }

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", designation='" + designation + '\'' +
                ", description='" + description + '\'' +
                ", Prestataire=" + Prestataire +
                '}';
    }
}
