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
    @Enumerated(EnumType.STRING)
    private ESituationMaritale situationMaritale;
    private Integer nbEnfant;
    private String image;
    private LocalDate dateEntree;
    private String ripIBAN;
    private String poste;
    @OneToOne
    private Personne personne;
    @Enumerated(EnumType.STRING)
    private ETypeEmployer typeEmployer;

    public Employer() {
    }

    public Employer(String numeroCNSS, String observation, ESituationMaritale situationMaritale, Integer nbEnfant, String image, LocalDate dateEntree, String ripIBAN, String poste, Personne personne, ETypeEmployer typeEmployer) {
        this.numeroCNSS = numeroCNSS;
        this.observation = observation;
        this.situationMaritale = situationMaritale;
        this.nbEnfant = nbEnfant;
        this.image = image;
        this.dateEntree = dateEntree;
        this.ripIBAN = ripIBAN;
        this.poste = poste;
        this.personne = personne;
        this.typeEmployer = typeEmployer;
    }

    public Employer(Integer id, String numeroCNSS, String observation, ESituationMaritale situationMaritale, Integer nbEnfant, String image, LocalDate dateEntree, String ripIBAN, String poste, Personne personne, ETypeEmployer typeEmployer) {
        this.id = id;
        this.numeroCNSS = numeroCNSS;
        this.observation = observation;
        this.situationMaritale = situationMaritale;
        this.nbEnfant = nbEnfant;
        this.image = image;
        this.dateEntree = dateEntree;
        this.ripIBAN = ripIBAN;
        this.poste = poste;
        this.personne = personne;
        this.typeEmployer = typeEmployer;
    }

    public Integer id() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String numeroCNSS() {
        return numeroCNSS;
    }

    public void setNumeroCNSS(String numeroCNSS) {
        this.numeroCNSS = numeroCNSS;
    }

    public String observation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public ESituationMaritale situationMaritale() {
        return situationMaritale;
    }

    public void setSituationMaritale(ESituationMaritale situationMaritale) {
        this.situationMaritale = situationMaritale;
    }

    public Integer nbEnfant() {
        return nbEnfant;
    }

    public void setNbEnfant(Integer nbEnfant) {
        this.nbEnfant = nbEnfant;
    }

    public String image() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LocalDate dateEntree() {
        return dateEntree;
    }

    public void setDateEntree(LocalDate dateEntree) {
        this.dateEntree = dateEntree;
    }

    public String ripIBAN() {
        return ripIBAN;
    }

    public void setRipIBAN(String ripIBAN) {
        this.ripIBAN = ripIBAN;
    }

    public String poste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public Personne personne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public ETypeEmployer typeEmployer() {
        return typeEmployer;
    }

    public void setTypeEmployer(ETypeEmployer typeEmployer) {
        this.typeEmployer = typeEmployer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employer)) return false;
        Employer employer = (Employer) o;
        return id.equals(employer.id) && numeroCNSS.equals(employer.numeroCNSS) && observation.equals(employer.observation) && situationMaritale == employer.situationMaritale && nbEnfant.equals(employer.nbEnfant) && image.equals(employer.image) && dateEntree.equals(employer.dateEntree) && ripIBAN.equals(employer.ripIBAN) && poste.equals(employer.poste) && personne.equals(employer.personne) && typeEmployer == employer.typeEmployer;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numeroCNSS, observation, situationMaritale, nbEnfant, image, dateEntree, ripIBAN, poste, personne, typeEmployer);
    }

    @Override
    public String toString() {
        return "Employer{" +
                "id=" + id +
                ", numeroCNSS='" + numeroCNSS + '\'' +
                ", observation='" + observation + '\'' +
                ", situationMaritale=" + situationMaritale +
                ", nbEnfant=" + nbEnfant +
                ", image='" + image + '\'' +
                ", dateEntree=" + dateEntree +
                ", ripIBAN='" + ripIBAN + '\'' +
                ", poste='" + poste + '\'' +
                ", personne=" + personne +
                ", typeEmployer=" + typeEmployer +
                '}';
    }
}
