package tn.essatin.erp.payload.request.financier;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class InscriptionEmployerByIdPersonneRequest {
    @Min(value = 1,message = "id personne est strictemt positif")
    int idPersonne;
    @Pattern(regexp = "([0-9]{10})", message = "Numero de Securité sociale doit contenir 10 chiffres")
    private final String numeroCNSS;
    private final String observationEmployer;
    @NotBlank(message = "la situation mariétale ne peut etre vide")
    private final String situationMaritale;
    @NotNull(message = "Le nombre d'enfants ne peut etre null")
    private final int nbEnfant;
    private final String image;
    @Past(message = "la date de naissance doit etre au passé!")
    private final LocalDate dateEntree;
    @Pattern(regexp = "([0-9A-Za-z]{15,24})", message = "Veillez verifier le numero de comptes")
    private final String ripIBAN;
    @NotBlank(message = "le poste ne peut etre vide")
    private final String poste;
    @NotBlank(message = "le type employer ne peut etre vide")
    private final String typeEmployer;

    @NotBlank(message = "Ennumeration de type 'ETypeContrat'")
    private final String typeContrat;
    @NotBlank(message = "Ennumeration de type 'EUniteSalaire'")
    private final String uniteSalaire;
    @NotNull(message = "prixUnite ne peut etre null")
    private final Double prixUnite;
    private final LocalDate dateDebutContrat;
    private final LocalDate dateFinContrat;
    private final LocalDate dateSignatureContrat;
    private final LocalDate dateResiliationContrat;
    private final String observationContrat;

    public InscriptionEmployerByIdPersonneRequest(int idPersonne, String numeroCNSS,
                                                  String observationEmployer, String situationMaritale,
                                                  int nbEnfant, String image, LocalDate dateEntree, String ripIBAN,
                                                  String poste, String typeEmployer, String typeContrat,
                                                  String uniteSalaire, Double prixUnite, LocalDate dateDebutContrat,
                                                  LocalDate dateFinContrat, LocalDate dateSignatureContrat,
                                                  LocalDate dateResiliationContrat, String observationContrat) {
        this.idPersonne = idPersonne;
        this.numeroCNSS = numeroCNSS;
        this.observationEmployer = observationEmployer;
        this.situationMaritale = situationMaritale;
        this.nbEnfant = nbEnfant;
        this.image = image;
        this.dateEntree = dateEntree;
        this.ripIBAN = ripIBAN;
        this.poste = poste;
        this.typeEmployer = typeEmployer;
        this.typeContrat = typeContrat;
        this.uniteSalaire = uniteSalaire;
        this.prixUnite = prixUnite;
        this.dateDebutContrat = dateDebutContrat;
        this.dateFinContrat = dateFinContrat;
        this.dateSignatureContrat = dateSignatureContrat;
        this.dateResiliationContrat = dateResiliationContrat;
        this.observationContrat = observationContrat;
    }

    public int getIdPersonne() {
        return idPersonne;
    }

    public String getNumeroCNSS() {
        return numeroCNSS;
    }

    public String getObservationEmployer() {
        return observationEmployer;
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

    public String getTypeEmployer() {
        return typeEmployer;
    }

    public String getTypeContrat() {
        return typeContrat;
    }

    public String getUniteSalaire() {
        return uniteSalaire;
    }

    public Double getPrixUnite() {
        return prixUnite;
    }

    public LocalDate getDateDebutContrat() {
        return dateDebutContrat;
    }

    public LocalDate getDateFinContrat() {
        return dateFinContrat;
    }

    public LocalDate getDateSignatureContrat() {
        return dateSignatureContrat;
    }

    public LocalDate getDateResiliationContrat() {
        return dateResiliationContrat;
    }

    public String getObservationContrat() {
        return observationContrat;
    }
}
