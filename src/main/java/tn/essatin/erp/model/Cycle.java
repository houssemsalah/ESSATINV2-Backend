package tn.essatin.erp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cycle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCycle;
    private String designation;
    private String description;

    public Cycle(Integer idCycle, String designation, String description) {
        this.idCycle = idCycle;
        this.designation = designation;
        this.description = description;
    }

    public Cycle() {
    }

    public Integer getIdCycle() {
        return idCycle;
    }

    public void setIdCycle(Integer idCycle) {
        this.idCycle = idCycle;
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
        return "Cycle{" +
                "idCycle=" + idCycle +
                ", designation='" + designation + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
