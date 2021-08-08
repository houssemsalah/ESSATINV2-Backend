package tn.essatin.erp.model.financier;

import tn.essatin.erp.model.Personne;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Employer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String numeroCNSS;
    private String observation;
    private String situationMaritale;
    private Integer nbEnfant;
    private String image;
    private LocalDate dateEntree;
    private String ripIBAN;
    private String img;
    private String poste;

    @ManyToOne
    private Personne personne;
    @Enumerated(EnumType.STRING)
    private ETypeEmployer typeEmployer;

    public Employer() {
    }

    public Employer(String numeroCNSS, String observation, String situationMaritale, Integer nbEnfant, String image, LocalDate dateEntree, Personne personne, ETypeEmployer typeEmployer) {
        this.numeroCNSS = numeroCNSS;
        this.observation = observation;
        this.situationMaritale = situationMaritale;
        this.nbEnfant = nbEnfant;
        this.image = image;
        this.dateEntree = dateEntree;
        this.personne = personne;
        this.typeEmployer = typeEmployer;
    }

    public Employer(Integer id, String numeroCNSS, String observation, String situationMaritale, Integer nbEnfant, String image, LocalDate dateEntree, Personne personne, ETypeEmployer typeEmployer) {
        this.id = id;
        this.numeroCNSS = numeroCNSS;
        this.observation = observation;
        this.situationMaritale = situationMaritale;
        this.nbEnfant = nbEnfant;
        this.image = image;
        this.dateEntree = dateEntree;
        this.personne = personne;
        this.typeEmployer = typeEmployer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumeroCNSS() {
        return numeroCNSS;
    }

    public void setNumeroCNSS(String numeroCNSS) {
        this.numeroCNSS = numeroCNSS;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getSituationMaritale() {
        return situationMaritale;
    }

    public void setSituationMaritale(String situationMaritale) {
        this.situationMaritale = situationMaritale;
    }

    public Integer getNbEnfant() {
        return nbEnfant;
    }

    public void setNbEnfant(Integer nbEnfant) {
        this.nbEnfant = nbEnfant;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
                ", numeroCNSS='" + numeroCNSS + '\'' +
                ", observation='" + observation + '\'' +
                ", situationMaritale='" + situationMaritale + '\'' +
                ", nbEnfant=" + nbEnfant +
                ", image='" + image + '\'' +
                ", dateEntree=" + dateEntree +
                ", personne=" + personne +
                ", typeEmployer=" + typeEmployer +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employer)) return false;
        Employer employer = (Employer) o;
        return getId().equals(employer.getId()) &&
                getNumeroCNSS().equals(employer.getNumeroCNSS()) &&
                getObservation().equals(employer.getObservation()) &&
                getSituationMaritale().equals(employer.getSituationMaritale()) &&
                getNbEnfant().equals(employer.getNbEnfant()) &&
                getImage().equals(employer.getImage()) &&
                getDateEntree().equals(employer.getDateEntree()) &&
                getPersonne().equals(employer.getPersonne()) &&
                getTypeEmployer() == employer.getTypeEmployer();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNumeroCNSS(), getObservation(), getSituationMaritale(), getNbEnfant(), getImage(), getDateEntree(), getPersonne(), getTypeEmployer());
    }
}
