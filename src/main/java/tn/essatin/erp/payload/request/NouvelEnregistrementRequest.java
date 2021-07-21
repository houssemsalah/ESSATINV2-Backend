package tn.essatin.erp.payload.request;

import javax.validation.constraints.Min;

public class NouvelEnregistrementRequest {
    @Min(value = 1, message = "la valeur 'idEtudiant' ne peut etre null ou inferieur a 1")
    int idEtudiant;
    @Min(value = 1, message = "la valeur 'niveauxInscrit' ne peut etre null ou inferieur a 1")
    int niveauxInscrit;

    public int getIdEtudiant() {
        return idEtudiant;
    }

    public int getNiveauxInscrit() {
        return niveauxInscrit;
    }
}
