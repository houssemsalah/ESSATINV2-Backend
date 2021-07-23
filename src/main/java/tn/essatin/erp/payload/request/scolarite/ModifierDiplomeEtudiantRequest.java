package tn.essatin.erp.payload.request.scolarite;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class ModifierDiplomeEtudiantRequest {
    @Min(value = 1, message = "le champ 'idDiplomeEtudiant' est obligatoire et doit etre strictement supperieur a 1")
    int idDiplomeEtudiant;
    @Min(value = 1, message = "le champ 'idDiplome' est obligatoire et doit etre strictement supperieur a 1")
    int idDiplome;
    @Size(min = 4, max = 4, message = "annee invalide")
    @Digits(fraction = 0, integer = 4, message = "annee invalide")
    String annee;
    @NotEmpty(message = "Le specialite ne doit pas etre vide")
    String specialite;
    @Min(value = 1, message = "le champ 'niveau' est obligatoire et doit etre strictement supperieur a 1")
    int niveau;
    @NotEmpty(message = "Le status ne doit pas etre vide")
    String status;
    @NotEmpty(message = "L'etablissement ne doit pas etre vide")
    String etablissement;

    public int getIdDiplomeEtudiant() {
        return idDiplomeEtudiant;
    }

    public int getIdDiplome() {
        return idDiplome;
    }

    public String getAnnee() {
        return annee;
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

    public String getEtablissement() {
        return etablissement;
    }
}
