package tn.essatin.erp.payload.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class DiplomeEtudiantByIdRequest {

    @NotNull
    @Min(1)
    int idDiplomeEtudiant;

    public int getIdDiplomeEtudiant() {
        return idDiplomeEtudiant;
    }

    public void setIdDiplomeEtudiant(int idDiplomeEtudiant) {
        this.idDiplomeEtudiant = idDiplomeEtudiant;
    }
}
