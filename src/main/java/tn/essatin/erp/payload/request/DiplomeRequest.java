package tn.essatin.erp.payload.request;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class DiplomeRequest {

    @NotBlank(message = "le nomDiplome ne peut etre vide")
    String nomDiplome;
    @Size(min = 4, max = 4, message = "annee invalide")
    @Digits(fraction = 0, integer = 4, message = "annee invalide")
    String annee;
    @NotBlank(message = "la specialité ne peut etre vide")
    String specialite;
    @Min(value = 1, message = "niveaux doit etre supperieur a 1")
    int niveau;
    @NotBlank(message = "la statut ne peut etre vide")
    String status;
    @NotBlank(message = "l'etablissement ne peut etre vide")
    String etablissement;

    public String getNomDiplome() {
        return nomDiplome;
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
