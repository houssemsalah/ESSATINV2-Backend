package tn.essatin.erp.model;

import tn.essatin.erp.model.Scolarite.Cycle;

import javax.persistence.*;

@Entity
public class FormuleDeCalcul {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFormule;

    @OneToOne
    private Cycle cycle ;
    @OneToOne
    private Matiere matiere;
    private double coefExamen;
    private  double coefDs;
    private double coefTp;
    private double coefOrale;


    public FormuleDeCalcul() {
    }

    public FormuleDeCalcul(Integer idFormule, Cycle cycle, Matiere matiere, double coefExamen, double coefDs, double coefTp, double coefOrale) {
        this.idFormule = idFormule;
        this.cycle = cycle;
        this.matiere = matiere;
        this.coefExamen = coefExamen;
        this.coefDs = coefDs;
        this.coefTp = coefTp;
        this.coefOrale = coefOrale;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    public Integer getIdFormule() {
        return idFormule;
    }

    public void setIdFormule(Integer idFormule) {
        this.idFormule = idFormule;
    }

    public Cycle getCycle() {
        return cycle;
    }

    public void setCycle(Cycle cycle) {
        this.cycle = cycle;
    }

    public double getCoefExamen() {
        return coefExamen;
    }

    public void setCoefExamen(double coefExamen) {
        this.coefExamen = coefExamen;
    }

    public double getCoefDs() {
        return coefDs;
    }

    public void setCoefDs(double coefDs) {
        this.coefDs = coefDs;
    }

    public double getCoefTp() {
        return coefTp;
    }

    public void setCoefTp(double coefTp) {
        this.coefTp = coefTp;
    }

    public double getCoefOrale() {
        return coefOrale;
    }

    public void setCoefOrale(double coefOrale) {
        this.coefOrale = coefOrale;
    }
}
