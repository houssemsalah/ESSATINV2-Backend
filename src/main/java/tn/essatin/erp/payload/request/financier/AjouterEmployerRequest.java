package tn.essatin.erp.payload.request.financier;

import tn.essatin.erp.model.Personne;
import tn.essatin.erp.model.financier.ESituationMaritale;
import tn.essatin.erp.model.financier.ETypeEmployer;

import javax.persistence.*;
import java.time.LocalDate;

public class AjouterEmployerRequest {
    private String numeroCNSS;
    private String observation;
    private String situationMaritale;
    private int nbEnfant;
    private String image;
    private LocalDate dateEntree;
    private String ripIBAN;
    private String poste;
    private int idpersonne;
    private String typeEmployer;

    public AjouterEmployerRequest(String numeroCNSS,
                                  String observation,
                                  String situationMaritale,
                                  int nbEnfant,
                                  String image,
                                  LocalDate dateEntree,
                                  String ripIBAN,
                                  String poste,
                                  int idpersonne,
                                  String typeEmployer) {
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

    public String getObservation() {
        return observation;
    }

    public String getSituationMaritale() {
        return situationMaritale;
    }

    public int getNbEnfant() {
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

    public int getIdpersonne() {
        return idpersonne;
    }

    public String getTypeEmployer() {
        return typeEmployer;
    }
}
