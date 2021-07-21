package tn.essatin.erp.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table
public class Personne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPersonne;
    private String nom;
    private String prenom;
    private String mail;
    private String adresse;
    private String tel;
    private LocalDate dateDeNaissance;
    private String lieuDeNaissance;
    @OneToOne(cascade = CascadeType.ALL)
    private TypeIdentificateur idTypeIdentificateur;
    private String numeroIdentificateur;
    private String sexe;
    @OneToOne(cascade = CascadeType.ALL)
    private Nationalite idNationalite;


    public Personne(String nom, String prenom, String mail, String adresse,
                    String tel, LocalDate dateDeNaissance, String lieuDeNaissance,
                    TypeIdentificateur id_Type_Identificateur, String numeroIdentificateur, String sexe,
                    Nationalite ID_Nationalite) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.adresse = adresse;
        this.tel = tel;
        this.dateDeNaissance = dateDeNaissance;
        this.lieuDeNaissance = lieuDeNaissance;
        this.idTypeIdentificateur = id_Type_Identificateur;
        this.numeroIdentificateur = numeroIdentificateur;
        this.sexe = sexe;
        this.idNationalite = ID_Nationalite;
    }

    public Personne() {
    }

    public Integer getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(Integer ID_Personne) {
        this.idPersonne = ID_Personne;
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

    public TypeIdentificateur getIdIdentificateur() {
        return idTypeIdentificateur;
    }

    public void setIdIdentificateur(TypeIdentificateur idTypeIdentificateur) {
        this.idTypeIdentificateur = idTypeIdentificateur;
    }

    public String getNumeroIdentificateur() {
        return numeroIdentificateur;
    }

    public void setNumeroIdentificateur(String numero_Identificateur) {
        this.numeroIdentificateur = numero_Identificateur;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Nationalite getIdNationalite() {
        return idNationalite;
    }

    public void setIdNationalite(Nationalite ID_Nationalite) {
        this.idNationalite = ID_Nationalite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Personne personne = (Personne) o;
        return idPersonne.equals(personne.idPersonne);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPersonne);
    }

    @Override
    public String toString() {
        return "Personne{" +
                "ID_Personne=" + idPersonne +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", mail='" + mail + '\'' +
                ", adresse='" + adresse + '\'' +
                ", tel='" + tel + '\'' +
                ", DateDeNaissance=" + dateDeNaissance +
                ", LieuDeNaissance='" + lieuDeNaissance + '\'' +
                ", Id_Identificateur=" + idTypeIdentificateur +
                ", numero_Identificateur='" + numeroIdentificateur + '\'' +
                ", sexe='" + sexe + '\'' +
                ", ID_Nationalite=" + idNationalite +
                '}';
    }
}
