package tn.essatin.erp.payload.request;

import tn.essatin.erp.model.ContactEtudiant;
import tn.essatin.erp.model.DiplomeEtudiant;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.List;

public class InscriptionRequest {
    @NotBlank
    String nom;
    @NotBlank
    String prenom;
    @NotBlank
    String mail;
    @NotBlank
    String adresse;
    @NotBlank
    String telephonne;
    @Past
    LocalDate dateNaissance;
    @NotBlank
    String lieuNaissance;
    @NotNull
    int idTypeIdentificateur;
    @NotBlank
    String ididentif;
    @NotBlank
    String sexe;
    @NotNull
    int idNationalite;
    @NotNull
    List<ContactEtudiant> contactEtudiantList;
    @NotNull
    List<DiplomeEtudiant> diplomeEtudiantList;
    @NotNull
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

    public List<ContactEtudiant> getContactEtudiantList() {
        return contactEtudiantList;
    }

    public void setContactEtudiantList(List<ContactEtudiant> contactEtudiantList) {
        this.contactEtudiantList = contactEtudiantList;
    }

    public List<DiplomeEtudiant> getDiplomeEtudiantList() {
        return diplomeEtudiantList;
    }

    public void setDiplomeEtudiantList(List<DiplomeEtudiant> diplomeEtudiantList) {
        this.diplomeEtudiantList = diplomeEtudiantList;
    }

    public int getNiveauxInscrit() {
        return niveauxInscrit;
    }

    public void setNiveauxInscrit(int niveauxInscrit) {
        this.niveauxInscrit = niveauxInscrit;
    }
}
