package tn.essatin.erp.model.financier;

import tn.essatin.erp.model.Personne;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Employer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String NCNSS;
    private String observation;
    private String situationM;
    private Integer NBEnfant;
    private String IMG;
    private LocalDate dateEntree;
    @ManyToOne
    private Personne personne;
    @Enumerated(EnumType.STRING)
    private ETypeEmployer typeEmployer;


    public Employer(String NCNSS, String observation, String situationM,
                    Integer NBEnfant, String IMG, LocalDate dateEntree, Personne personne,
                    ETypeEmployer typeEmployer) {
        this.NCNSS = NCNSS;
        this.observation = observation;
        this.situationM = situationM;
        this.NBEnfant = NBEnfant;
        this.IMG = IMG;
        this.dateEntree = dateEntree;
        this.personne = personne;
        this.typeEmployer = typeEmployer;
    }

    public Employer() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public ETypeEmployer getTypeEmployer() {
        return typeEmployer;
    }

    public void setTypeEmployer(ETypeEmployer typeEmployer) {
        this.typeEmployer = typeEmployer;
    }

    @Override
    public String toString() {
        return "Employer{" +
                "id=" + id +
                ", NCNSS='" + NCNSS + '\'' +
                ", observation='" + observation + '\'' +
                ", situationM='" + situationM + '\'' +
                ", NBEnfant=" + NBEnfant +
                ", IMG='" + IMG + '\'' +
                ", dateEntree=" + dateEntree +
                ", personne=" + personne +
                ", typeEmployer=" + typeEmployer +
                '}';
    }
}
