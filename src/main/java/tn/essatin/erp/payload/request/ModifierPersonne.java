package tn.essatin.erp.payload.request;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class ModifierPersonne {
    @Min(value = 1, message = "le champ idPersonne est obligatoire")
    Integer idPersonne;
    @NotBlank(message = "Le nom est obligatoire")
    String nom;
    @NotBlank(message = "Le prenom est obligatoire")
    String prenom;
    @Email(message = "l'Email doit etre valid")
    String mail;
    @NotBlank(message = "L'addresse est obligatoire")
    String adresse;
    @Pattern(regexp = "([0-9]*)", message = "Numero de telephonne ne peut contenir que des chiffres")
    @Size(min = 8, max = 15,message = "Numero de telephonne doit etre entre 8 et 15 chiffres")
    String tel;
    @Past(message = "la date de naissance doit etre au passe")
    LocalDate dateDeNaissance;
    @NotBlank(message = "lieux de naissance ne peut etre vide")
    String lieuDeNaissance;
    @Min(value = 1, message = "le champ idTypeIdentificateur est obligatoire")
    int idTypeIdentificateur;
    @Pattern(regexp = "(([a-zA-Z0-9]{8,9})?)", message = "message = champ numeroIdentificateur invalide")
    String numeroIdentificateur;
    @NotBlank(message = "le sexe ne peut etre vide")
    String sexe;
    @Min(value = 1, message = "le champ idNationalite est obligatoire")
    int idNationalite;

    public Integer getIdPersonne() {
        return idPersonne;
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

    public String getMail() {
        return mail;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getTel() {
        return tel;
    }

    public LocalDate getDateDeNaissance() {
        return dateDeNaissance;
    }

    public String getLieuDeNaissance() {
        return lieuDeNaissance;
    }

    public int getIdTypeIdentificateur() {
        return idTypeIdentificateur;
    }

    public String getNumeroIdentificateur() {
        return numeroIdentificateur;
    }

    public String getSexe() {
        return sexe;
    }

    public int getIdNationalite() {
        return idNationalite;
    }

    public void setNumeroIdentificateur(String numeroIdentificateur) {
        this.numeroIdentificateur = numeroIdentificateur;
    }
}
