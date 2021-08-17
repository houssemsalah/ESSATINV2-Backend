package tn.essatin.erp.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

public class SignupRequest {
    @NotBlank
    private String login;
    @NotBlank
    private String password;
    @NotBlank
    private String nom;
    @NotBlank
    private String prenom;
    @NotBlank
    @Email
    private String mail;
    @NotBlank
    private String adresse;
    @NotBlank
    private String tel;
    @NotBlank
    private String dateDeNaissance;
    @NotBlank
    private String lieuDeNaissance;
    @NotNull
    private int typeIdentificateur;
    @NotBlank
    private String numeroIdentificateur;
    @NotBlank
    private String sexe;
    @NotBlank
    private String nationalite;
    private Set<String> role;

    public int getTypeIdentificateur() {
        return typeIdentificateur;
    }

    public void setTypeIdentificateur(int typeIdentificateur) {
        this.typeIdentificateur = typeIdentificateur;
    }

    public String getNumeroIdentificateur() {
        return numeroIdentificateur;
    }

    public void setNumeroIdentificateur(String numeroIdentificateur) {
        this.numeroIdentificateur = numeroIdentificateur;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(String dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public String getLieuDeNaissance() {
        return lieuDeNaissance;
    }

    public void setLieuDeNaissance(String lieuDeNaissance) {
        this.lieuDeNaissance = lieuDeNaissance;
    }


    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public Set<String> getRole() {
        return role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }
}
