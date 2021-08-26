package tn.essatin.erp.payload.request.financier;

import tn.essatin.erp.model.Personne;
import tn.essatin.erp.model.financier.ESituationMaritale;
import tn.essatin.erp.model.financier.ETypeEmployer;

import javax.persistence.*;
import java.time.LocalDate;

public class AjouterEmployerRequest {
    private String numeroCNSS;
    private String observation;
    private ESituationMaritale situationMaritale;
    private int nbEnfant;
    private String image;
    private LocalDate dateEntree;
    private String ripIBAN;
    private String poste;
    private int idpersonne;
    private ETypeEmployer typeEmployer;

    public AjouterEmployerRequest(String numeroCNSS, String observation, ESituationMaritale situationMaritale, int nbEnfant, String image, LocalDate dateEntree, String ripIBAN, String poste, int idpersonne, ETypeEmployer typeEmployer) {
        this.numeroCNSS = numeroCNSS;
        this.observation = observation;
        this.situationMaritale = situationMaritale;
        this.nbEnfant = nbEnfant;
        this.image = image;
        this.dateEntree = dateEntree;
        this.ripIBAN = ripIBAN;
        this.poste = poste;
        this.idpersonne = idpersonne;
        this.typeEmployer = typeEmployer;
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

    public ESituationMaritale getSituationMaritale() {
        return situationMaritale;
    }

    public void setSituationMaritale(ESituationMaritale situationMaritale) {
        this.situationMaritale = situationMaritale;
    }

    public int getNbEnfant() {
        return nbEnfant;
    }

    public void setNbEnfant(int nbEnfant) {
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

    public String getRipIBAN() {
        return ripIBAN;
    }

    public void setRipIBAN(String ripIBAN) {
        this.ripIBAN = ripIBAN;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public int getIdpersonne() {
        return idpersonne;
    }

    public void setIdpersonne(int idpersonne) {
        this.idpersonne = idpersonne;
    }

    public ETypeEmployer getTypeEmployer() {
        return typeEmployer;
    }

    public void setTypeEmployer(ETypeEmployer typeEmployer) {
        this.typeEmployer = typeEmployer;
    }
}
