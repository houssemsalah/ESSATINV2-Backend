package tn.essatin.erp.payload.request;

import javax.validation.constraints.Min;
public class DiplomeEtudiantByIdEtudiantRequest {

    @Min(value = 1,message = "la variable 'idEtudiant' est obligatoiret doit etre superieur a 0")
    int idEtudiant;

    public int getIdEtudiant() {
        return idEtudiant;
    }

}
