package tn.essatin.erp.model.Scolarite;

import javax.persistence.*;

@Entity
public class Parcours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idParcours;
    @ManyToOne(cascade = CascadeType.ALL)
    private Specialite specialite;
    private String designation;
    private String description;

    public Parcours(Integer idParcours, Specialite specialite, String designation, String description) {
        this.idParcours = idParcours;
        this.specialite = specialite;
        this.designation = designation;
        this.description = description;
    }

    public Parcours() {
    }

    public Integer getIdParcours() {
        return idParcours;
    }

    public void setIdParcours(Integer idParcours) {
        this.idParcours = idParcours;
    }

    public Specialite getSpecialite() {
        return specialite;
    }

    public void setSpecialite(Specialite specialite) {
        this.specialite = specialite;
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

    @Override
    public String toString() {
        return "Parcours{" +
                "idParcours=" + idParcours +
                ", specialite=" + specialite +
                ", designation='" + designation + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
