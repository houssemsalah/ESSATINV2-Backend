package tn.essatin.erp.model.financier;

import tn.essatin.erp.model.Personne;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Employer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEmployer;
    private String NCNSS;
    private String observation;
    private String situationM;
    private Integer NBEnfant;
    private String IMG;
    private LocalDate dateEntree;
    @ManyToOne
    private Personne idPersonne;
    @ManyToOne
    private Salarie idSalarie;


    public Employer(String NCNSS, String observation, String situationM, Integer NBEnfant, String IMG, LocalDate dateEntree, Personne idPersonne, Salarie idSalarie) {
        this.NCNSS = NCNSS;
        this.observation = observation;
        this.situationM = situationM;
        this.NBEnfant = NBEnfant;
        this.IMG = IMG;
        this.dateEntree = dateEntree;
        this.idPersonne = idPersonne;
        this.idSalarie= idSalarie;
    }

    public Employer() {
    }

    public Integer getIdEmployer() {
        return idEmployer;
    }

    public void setIdEmployer(Integer idEmployer) {
        this.idEmployer = idEmployer;
    }

    public String getNCNSS() {
        return NCNSS;
    }

    public void setNCNSS(String NCNSS) {
        this.NCNSS = NCNSS;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getSituationM() {
        return situationM;
    }

    public void setSituationM(String situationM) {
        this.situationM = situationM;
    }

    public Integer getNBEnfant() {
        return NBEnfant;
    }

    public void setNBEnfant(Integer NBEnfant) {
        this.NBEnfant = NBEnfant;
    }

    public String getIMG() {
        return IMG;
    }

    public void setIMG(String IMG) {
        this.IMG = IMG;
    }

    public LocalDate getDateEntree() {
        return dateEntree;
    }

    public void setDateEntree(LocalDate dateEntree) {
        this.dateEntree = dateEntree;
    }

    public Personne getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(Personne idPersonne) {
        this.idPersonne = idPersonne;
    }

    public Salarie getIdSalarie() {
        return idSalarie;
    }

    public void setIdSalarie(Salarie idSalarie) {
        this.idSalarie = idSalarie;
    }

    @Override
    public String toString() {
        return "Employer{" +
                "idEmployer=" + idEmployer +
                ", NCNSS='" + NCNSS + '\'' +
                ", observation='" + observation + '\'' +
                ", situationM='" + situationM + '\'' +
                ", NBEnfant=" + NBEnfant +
                ", IMG='" + IMG + '\'' +
                ", dateEntree=" + dateEntree +
                ", idPersonne=" + idPersonne +
                ", idSalarie=" + idSalarie +
                '}';
    }
}
