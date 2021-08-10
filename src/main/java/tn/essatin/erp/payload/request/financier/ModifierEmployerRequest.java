package tn.essatin.erp.payload.request.financier;

import tn.essatin.erp.model.Personne;
import tn.essatin.erp.model.financier.ESituationMaritale;
import tn.essatin.erp.model.financier.ETypeEmployer;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

public class ModifierEmployerRequest {

    @Min(value = 1,message = "Le champs id est obligatoire et ne peut etre inferieur a 1")
    private Integer id;
    @Pattern(regexp = "([0-9]*)", message = "l'identifiant unique ne peut contenir que des chiffres")
    @Size(min = 10, max = 15, message = "l'identifiant unique doit etre entre 10 et 15 chiffres")
    private String numeroCNSS;
    @NotBlank
    private String observation;
    @NotBlank
    private String situationMaritale;
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
    private String typeEmployer;

    public ModifierEmployerRequest(Integer id,
                                   String numeroCNSS,
                                   String observation,
                                   String situationMaritale,
                                   int nbEnfant, String image,
                                   LocalDate dateEntree,
                                   String ripIBAN,
                                   String poste,
                                   int idPersonne,
                                   String typeEmployer) {
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

    public Integer getId() {
        return id;
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

    public int getIdPersonne() {
        return idPersonne;
    }

    public String getTypeEmployer() {
        return typeEmployer;
    }
}
