package tn.essatin.erp.payload.request.financier;


import tn.essatin.erp.model.financier.ESituationMaritale;
import tn.essatin.erp.model.financier.ETypeContrat;
import tn.essatin.erp.model.financier.ETypeEmployer;
import tn.essatin.erp.model.financier.EUniteSalaire;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class InscriptionEmployerRequest {
    @NotBlank(message = "Le nom est obligatoire")
    String nom;
    @NotBlank(message = "Le prenom est obligatoire")
    String prenom;
    @Email(message = "l'Email doit etre valid")
    String mail;
    @NotBlank(message = "L'addresse est obligatoire")
    String adresse;
    @Pattern(regexp = "([0-9]*)", message = "Numero de telephonne ne peut contenir que des chiffres")
    @Size(min = 8, max = 15, message = "Numero de telephonne doit etre entre 8 et 15 chiffres")
    String telephonne;
    @Past(message = "la date de naissance doit etre au passe")
    LocalDate dateNaissance;
    @NotBlank(message = "lieux de naissance ne peut etre vide")
    String lieuNaissance;
    @Min(value = 1, message = "le champ idTypeIdentificateur est obligatoire")
    int idTypeIdentificateur;
    @Size(min = 8, max = 9, message = "Numero ididentif invalide")
    String ididentif;
    @NotBlank(message = "le sexe ne peut etre vide")
    String sexe;
    @Min(value = 1, message = "le champ idNationalite est obligatoire")
    int idNationalite;

    @Pattern(regexp = "([0-9]{10})", message = "Numero de Securité sociale doit contenir 10 chiffres")
    private  String numeroCNSS;
    private  String observationEmployer;
    @NotBlank(message = "la situation mariétale ne peut etre vide")
    private ESituationMaritale situationMaritale;
    @NotNull(message = "Le nombre d'enfants ne peut etre null")
    private  int nbEnfant;
    private  String image;
    @Past(message = "la date de naissance doit etre au passé!")
    private  LocalDate dateEntree;
    @Pattern(regexp = "([0-9A-Za-z]{15,24})", message = "Veillez verifier le numero de comptes")
    private  String ripIBAN;
    @NotBlank(message = "le poste ne peut etre vide")
    private  String poste;
    @NotBlank(message = "le type employer ne peut etre vide")
    private ETypeEmployer typeEmployer;

    @NotBlank(message = "Ennumeration de type 'ETypeContrat'")
    private ETypeContrat typeContrat;
    @NotBlank(message = "Ennumeration de type 'EUniteSalaire'")
    private EUniteSalaire uniteSalaire;
    @NotNull(message = "prixUnite ne peut etre null")
    private  Double prixUnite;
    private  LocalDate dateDebutContrat;
    private  LocalDate dateFinContrat;
    private  LocalDate dateSignatureContrat;
    private  LocalDate dateResiliationContrat;
    private  String observationContrat;


    public InscriptionEmployerRequest(String nom, String prenom, String mail, String adresse, String telephonne,
                                      LocalDate dateNaissance, String lieuNaissance, int idTypeIdentificateur,
                                      String ididentif, String sexe, int idNationalite, String numeroCNSS, String observationEmployer,
                                      ESituationMaritale situationMaritale, int nbEnfant, String image, LocalDate dateEntree, String ripIBAN,
                                      String poste, ETypeEmployer typeEmployer, ETypeContrat typeContrat, EUniteSalaire uniteSalaire,
                                      Double prixUnite, LocalDate dateDebutContrat, LocalDate dateFinContrat, LocalDate dateSignatureContrat,
                                      LocalDate dateResiliationContrat, String observationContrat) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.adresse = adresse;
        this.telephonne = telephonne;
        this.dateNaissance = dateNaissance;
        this.lieuNaissance = lieuNaissance;
        this.idTypeIdentificateur = idTypeIdentificateur;
        this.ididentif = ididentif;
        this.sexe = sexe;
        this.idNationalite = idNationalite;
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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephonne() {
        return telephonne;
    }

    public void setTelephonne(String telephonne) {
        this.telephonne = telephonne;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getLieuNaissance() {
        return lieuNaissance;
    }

    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    public int getIdTypeIdentificateur() {
        return idTypeIdentificateur;
    }

    public void setIdTypeIdentificateur(int idTypeIdentificateur) {
        this.idTypeIdentificateur = idTypeIdentificateur;
    }

    public String getIdidentif() {
        return ididentif;
    }

    public void setIdidentif(String ididentif) {
        this.ididentif = ididentif;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public int getIdNationalite() {
        return idNationalite;
    }

    public void setIdNationalite(int idNationalite) {
        this.idNationalite = idNationalite;
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
