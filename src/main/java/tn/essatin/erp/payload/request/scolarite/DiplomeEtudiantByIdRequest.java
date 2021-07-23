package tn.essatin.erp.payload.request.scolarite;

import javax.validation.constraints.Min;

public class DiplomeEtudiantByIdRequest {

    @Min(value = 1,message = "Le champ est idDiplomeEtudiant obligatoire")
    int idDiplomeEtudiant;

    public int getIdDiplomeEtudiant() {
        return idDiplomeEtudiant;
    }
}
