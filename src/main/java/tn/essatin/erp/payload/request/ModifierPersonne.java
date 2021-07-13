package tn.essatin.erp.payload.request;

import tn.essatin.erp.model.Nationalite;
import tn.essatin.erp.model.TypeIdentificateur;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

public class ModifierPersonne {
    @NotNull
    @Min(1)
    Integer idPersonne;
    @NotBlank
    String nom;
    @NotBlank
    String prenom;
    @NotBlank
    String mail;
    @NotBlank
    String adresse;
    @NotBlank
    String tel;
    @NotNull
    @Past
    LocalDate dateDeNaissance;
    @NotBlank
    String lieuDeNaissance;
    @NotNull
    @Min(1)
    int idTypeIdentificateur;
    @NotBlank
    String numeroIdentificateur;
    @NotBlank
    String sexe;
    @NotNull
    @Min(1)
    int idNationalite;

    public Integer getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(Integer idPersonne) {
        this.idPersonne = idPersonne;
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public LocalDate getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(LocalDate dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public String getLieuDeNaissance() {
        return lieuDeNaissance;
    }

    public void setLieuDeNaissance(String lieuDeNaissance) {
        this.lieuDeNaissance = lieuDeNaissance;
    }

    public int getIdTypeIdentificateur() {
        return idTypeIdentificateur;
    }

    public void setIdTypeIdentificateur(int idTypeIdentificateur) {
        this.idTypeIdentificateur = idTypeIdentificateur;
    }

    public String getNumeroIdentificateur() {
        return numeroIdentificateur;
    }

    public void setNumeroIdentificateur(String numeroIdentificateur) {
        this.numeroIdentificateur = numeroIdentificateur;
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
}
