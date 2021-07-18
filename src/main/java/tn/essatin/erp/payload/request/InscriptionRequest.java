package tn.essatin.erp.payload.request;

import tn.essatin.erp.model.ContactEtudiant;
import tn.essatin.erp.model.DiplomeEtudiant;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

public class InscriptionRequest {
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
    @NotNull(message = "une list de ContactEtudiant doit etre fourni ")
    List<ContactEtudiant> contactEtudiantList;
    @NotNull(message = "une list de DiplomeEtudiant doit etre fourni ")
    List<DiplomeEtudiant> diplomeEtudiantList;
    @Min(value = 1, message = "le champ niveauxInscrit est obligatoire")
    int niveauxInscrit;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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

    public List<ContactEtudiant> getContactEtudiantList() {
        return contactEtudiantList;
    }

    public List<DiplomeEtudiant> getDiplomeEtudiantList() {
        return diplomeEtudiantList;
    }

    public int getNiveauxInscrit() {
        return niveauxInscrit;
    }
}
