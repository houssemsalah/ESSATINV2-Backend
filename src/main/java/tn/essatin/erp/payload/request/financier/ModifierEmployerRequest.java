package tn.essatin.erp.payload.request.financier;

import tn.essatin.erp.model.Personne;
import tn.essatin.erp.model.financier.ESituationMaritale;
import tn.essatin.erp.model.financier.ETypeEmployer;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

public class ModifierEmployerRequest {

    @Min(value = 1,message = "Le champs id est obligatoire et ne peut etre inferieur a 1")
    private int id;
    @Pattern(regexp = "([0-9]*)", message = "l'identifiant unique ne peut contenir que des chiffres")
    @Size(min = 10, max = 15, message = "l'identifiant unique doit etre entre 10 et 15 chiffres")
    private String numeroCNSS;
    @NotBlank
    private String observation;
    @NotBlank
    private ESituationMaritale situationMaritale;
    @Min(value = 0,message = "Nombre d'enfant ne peut etre négatif")
    private int nbEnfant;
    private String image;
    @NotNull
    private LocalDate dateEntree;
    @Pattern(regexp = "([0-9A-Z]*)", message = "l'identifiant unique ne peut contenir que des chiffres")
    @Size(min = 15, max = 30, message = "l'identifiant unique doit etre entre 10 et 15 chiffres")
    private String ripIBAN;
    @NotBlank
    private String poste;
    @NotNull
    private int idPersonne;
    @NotBlank
    private ETypeEmployer typeEmployer;

    public ModifierEmployerRequest(int id, String numeroCNSS, String observation, ESituationMaritale situationMaritale, int nbEnfant, String image, LocalDate dateEntree, String ripIBAN, String poste, int idPersonne, ETypeEmployer typeEmployer) {
        this.id = id;
        this.numeroCNSS = numeroCNSS;
        this.observation = observation;
        this.situationMaritale = situationMaritale;
        this.nbEnfant = nbEnfant;
        this.image = image;
        this.dateEntree = dateEntree;
        this.ripIBAN = ripIBAN;
        this.poste = poste;
        this.idPersonne = idPersonne;
        this.typeEmployer = typeEmployer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(int idPersonne) {
        this.idPersonne = idPersonne;
    }

    public ETypeEmployer getTypeEmployer() {
        return typeEmployer;
    }

    public void setTypeEmployer(ETypeEmployer typeEmployer) {
        this.typeEmployer = typeEmployer;
    }
}
