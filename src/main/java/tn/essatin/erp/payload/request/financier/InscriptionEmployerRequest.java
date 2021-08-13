package tn.essatin.erp.payload.request.financier;


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


    public InscriptionEmployerRequest(String nom, String prenom, String mail, String adresse,
                                      String telephonne, LocalDate dateNaissance, String lieuNaissance,
                                      int idTypeIdentificateur, String ididentif, String sexe,
                                      int idNationalite, String numeroCNSS, String observationEmployer,
                                      String situationMaritale, int nbEnfant, String image,
                                      LocalDate dateEntree, String ripIBAN, String poste, String typeEmployer,
                                      String typeContrat, String uniteSalaire, Double prixUnite,
                                      LocalDate dateDebutContrat, LocalDate dateFinContrat,
                                      LocalDate dateSignatureContrat, LocalDate dateResiliationContrat,
                                      String observationContrat) {
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

    public String getPrenom() {
        return prenom;
    }

    public String getMail() {
        return mail;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getTelephonne() {
        return telephonne;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public String getLieuNaissance() {
        return lieuNaissance;
    }

    public int getIdTypeIdentificateur() {
        return idTypeIdentificateur;
    }

    public String getIdidentif() {
        return ididentif;
    }

    public String getSexe() {
        return sexe;
    }

    public int getIdNationalite() {
        return idNationalite;
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
