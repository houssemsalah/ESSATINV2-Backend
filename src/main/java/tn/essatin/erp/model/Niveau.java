package tn.essatin.erp.model;

import javax.persistence.*;

@Entity
public class Niveau {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idNiveau;
    @ManyToOne(cascade = CascadeType.ALL)
    private Parcours parcours;
    private String designation;
    private String description;

    public Niveau(Integer idNiveau, Parcours parcours, String designation, String description) {
        this.idNiveau = idNiveau;
        this.parcours = parcours;
        this.designation = designation;
        this.description = description;
    }

    public Niveau() {
    }

    public Integer getIdNiveau() {
        return idNiveau;
    }

    public void setIdNiveau(Integer idNiveau) {
        this.idNiveau = idNiveau;
    }

    public Parcours getParcours() {
        return parcours;
    }

    public void setParcours(Parcours parcours) {
        this.parcours = parcours;
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
        return "Niveau{" +
                "idNiveau=" + idNiveau +
                ", parcours=" + parcours +
                ", designation='" + designation + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
