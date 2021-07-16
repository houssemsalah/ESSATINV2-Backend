package tn.essatin.erp.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ModifierDiplomeEtudiantRequest {
    @NotNull int idDiplomeEtudiant;
    @NotNull int idDiplome;
    @NotBlank String annee;
    @NotBlank String specialite;
    @NotNull int niveau;
    @NotBlank String status;
    @NotBlank String etablissement;

    public int getIdDiplomeEtudiant() {
        return idDiplomeEtudiant;
    }

    public void setIdDiplomeEtudiant(int idDiplomeEtudiant) {
        this.idDiplomeEtudiant = idDiplomeEtudiant;
    }

    public int getIdDiplome() {
        return idDiplome;
    }

    public void setIdDiplome(int idDiplome) {
        this.idDiplome = idDiplome;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(String etablissement) {
        this.etablissement = etablissement;
    }
}
