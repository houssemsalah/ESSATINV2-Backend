package tn.essatin.erp.payload.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class DiplomeEtudiantByIdEtudiantRequest {

    @NotNull
    @Min(1)
    int idEtudiant;

    public int getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(int idEtudiant) {
        this.idEtudiant = idEtudiant;
    }
}
