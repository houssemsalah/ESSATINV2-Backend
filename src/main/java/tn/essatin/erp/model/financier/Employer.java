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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNumeroCNSS(String numeroCNSS) {
        this.numeroCNSS = numeroCNSS;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public void setSituationMaritale(ESituationMaritale situationMaritale) {
        this.situationMaritale = situationMaritale;
    }

    public void setNbEnfant(Integer nbEnfant) {
        this.nbEnfant = nbEnfant;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDateEntree(LocalDate dateEntree) {
        this.dateEntree = dateEntree;
    }

    public void setRipIBAN(String ripIBAN) {
        this.ripIBAN = ripIBAN;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public void setTypeEmployer(ETypeEmployer typeEmployer) {
        this.typeEmployer = typeEmployer;
    }

    public Integer getId() {
        return id;
    }

    public String getNumeroCNSS() {
        return numeroCNSS;
    }

    public String getObservation() {
        return observation;
    }

    public ESituationMaritale getSituationMaritale() {
        return situationMaritale;
    }

    public Integer getNbEnfant() {
        return nbEnfant;
    }

    public String getImage() {
        return image;
    }

    public LocalDate getDateEntree() {
        return dateEntree;
    }

    public String getRipIBAN() {
        return ripIBAN;
    }

    public String getPoste() {
        return poste;
    }

    public Personne getPersonne() {
        return personne;
    }

    public ETypeEmployer getTypeEmployer() {
        return typeEmployer;
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
