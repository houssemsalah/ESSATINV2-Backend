package tn.essatin.erp.payload.request.financier;

import tn.essatin.erp.model.financier.ESituationMaritale;
import tn.essatin.erp.model.financier.ETypeContrat;
import tn.essatin.erp.model.financier.ETypeEmployer;
import tn.essatin.erp.model.financier.EUniteSalaire;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class InscriptionEmployerByIdPersonneRequest {
    @Min(value = 1,message = "id personne est strictemt positif")
    int idPersonne;
    @Pattern(regexp = "([0-9]{10})", message = "Numero de Securité sociale doit contenir 10 chiffres")
    private String numeroCNSS;
    private String observationEmployer;
    @NotNull(message = "la situation mariétale ne peut etre vide")
    private ESituationMaritale situationMaritale;
    @NotNull(message = "Le nombre d'enfants ne peut etre null")
    private int nbEnfant;
    private String image;
    @Past(message = "la date de naissance doit etre au passé!")
    private LocalDate dateEntree;
    @Pattern(regexp = "([0-9A-Za-z]{15,24})", message = "Veillez verifier le numero de comptes")
    private String ripIBAN;
    @NotBlank(message = "le poste ne peut etre vide")
    private String poste;
    @NotNull(message = "le type employer ne peut etre vide")
    private ETypeEmployer typeEmployer;

    @NotNull(message = "Ennumeration de type 'ETypeContrat'")
    private ETypeContrat typeContrat;
    @NotNull(message = "Ennumeration de type 'EUniteSalaire'")
    private EUniteSalaire uniteSalaire;
    @NotNull(message = "prixUnite ne peut etre null")
    private  Double prixUnite;
    private  LocalDate dateDebutContrat;
    private  LocalDate dateFinContrat;
    private  LocalDate dateSignatureContrat;
    private  LocalDate dateResiliationContrat;
    private  String observationContrat;

    public InscriptionEmployerByIdPersonneRequest(int idPersonne, String numeroCNSS, String observationEmployer,
                                                  ESituationMaritale situationMaritale, int nbEnfant, String image,
                                                  LocalDate dateEntree, String ripIBAN, String poste, ETypeEmployer typeEmployer, ETypeContrat typeContrat,
                                                  EUniteSalaire uniteSalaire, Double prixUnite, LocalDate dateDebutContrat, LocalDate dateFinContrat,
                                                  LocalDate dateSignatureContrat, LocalDate dateResiliationContrat,
                                                  String observationContrat) {
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

    public void setIdPersonne(int idPersonne) {
        this.idPersonne = idPersonne;
    }

    public String getNumeroCNSS() {
        return numeroCNSS;
    }

    public void setNumeroCNSS(String numeroCNSS) {
        this.numeroCNSS = numeroCNSS;
    }

    public String getObservationEmployer() {
        return observationEmployer;
    }

    public void setObservationEmployer(String observationEmployer) {
        this.observationEmployer = observationEmployer;
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

    public ETypeEmployer getTypeEmployer() {
        return typeEmployer;
    }

    public void setTypeEmployer(ETypeEmployer typeEmployer) {
        this.typeEmployer = typeEmployer;
    }

    public ETypeContrat getTypeContrat() {
        return typeContrat;
    }

    public void setTypeContrat(ETypeContrat typeContrat) {
        this.typeContrat = typeContrat;
    }

    public EUniteSalaire getUniteSalaire() {
        return uniteSalaire;
    }

    public void setUniteSalaire(EUniteSalaire uniteSalaire) {
        this.uniteSalaire = uniteSalaire;
    }

    public Double getPrixUnite() {
        return prixUnite;
    }

    public void setPrixUnite(Double prixUnite) {
        this.prixUnite = prixUnite;
    }

    public LocalDate getDateDebutContrat() {
        return dateDebutContrat;
    }

    public void setDateDebutContrat(LocalDate dateDebutContrat) {
        this.dateDebutContrat = dateDebutContrat;
    }

    public LocalDate getDateFinContrat() {
        return dateFinContrat;
    }

    public void setDateFinContrat(LocalDate dateFinContrat) {
        this.dateFinContrat = dateFinContrat;
    }

    public LocalDate getDateSignatureContrat() {
        return dateSignatureContrat;
    }

    public void setDateSignatureContrat(LocalDate dateSignatureContrat) {
        this.dateSignatureContrat = dateSignatureContrat;
    }

    public LocalDate getDateResiliationContrat() {
        return dateResiliationContrat;
    }

    public void setDateResiliationContrat(LocalDate dateResiliationContrat) {
        this.dateResiliationContrat = dateResiliationContrat;
    }

    public String getObservationContrat() {
        return observationContrat;
    }

    public void setObservationContrat(String observationContrat) {
        this.observationContrat = observationContrat;
    }
}
