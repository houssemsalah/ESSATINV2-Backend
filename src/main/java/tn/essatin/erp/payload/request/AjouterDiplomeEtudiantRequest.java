package tn.essatin.erp.payload.request;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AjouterDiplomeEtudiantRequest {

    @Min(value = 1, message = "le champ 'idDiplome' est obligatoire et doit etre strictement supperieur a zero")
    int idDiplome;
    @Min(value = 1, message = "le champ 'idEtudiant' est obligatoire et doit etre strictement supperieur a zero")
    int idEtudiant;
    @NotEmpty(message = "le champ 'annee' est obligatoire")
    String annee;
    @NotEmpty(message = "le champ 'specialite' est obligatoire")
    String specialite;
    @NotNull(message = "le champ 'niveau' est obligatoire")
    int niveau;
    @NotEmpty(message = "le champ 'status' est obligatoire")
    String status;
    @NotEmpty(message = "le champ 'etablissement' est obligatoire")
    String etablissement;

    public int getIdDiplome() {
        return idDiplome;
    }

    public int getIdEtudiant() {
        return idEtudiant;
    }

    public String getAnnee() {
        return annee;
    }

    public String getSpecialite() {
        return specialite;
    }

    public Integer getNiveau() {
        return niveau;
    }

    public String getStatus() {
        return status;
    }

    public String getEtablissement() {
        return etablissement;
    }
}
