package tn.essatin.erp.model;

import javax.persistence.*;

@Entity
public class Specialite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSpecialite;
    @ManyToOne(cascade = CascadeType.ALL)
    private Cycle cycle;
    private String designation;
    private String description;

    public Specialite(Integer idSpecialite, Cycle cycle, String designation, String description) {
        this.idSpecialite = idSpecialite;
        this.cycle = cycle;
        this.designation = designation;
        this.description = description;
    }

    public Specialite() {
    }

    public Integer getIdSpecialite() {
        return idSpecialite;
    }

    public void setIdSpecialite(Integer idSpecialite) {
        this.idSpecialite = idSpecialite;
    }

    public Cycle getCycle() {
        return cycle;
    }

    public void setCycle(Cycle cycle) {
        this.cycle = cycle;
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
        return "Specialite{" +
                "idSpecialite=" + idSpecialite +
                ", cycle=" + cycle +
                ", designation='" + designation + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
