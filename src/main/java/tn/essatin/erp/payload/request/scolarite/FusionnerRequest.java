package tn.essatin.erp.payload.request.scolarite;

import javax.validation.constraints.Min;

public class FusionnerRequest {

    @Min(value = 1, message = "la variable 'idOldPersonne' doit avoir une valeur strictement supperieur a 0")
    int idOldPersonne;
    @Min(value = 1, message = "la variable 'idNewPersonne' doit avoir une valeur strictement supperieur a 0")
    int idNewPersonne;

    public int getIdOldPersonne() {
        return idOldPersonne;
    }

    public int getIdNewPersonne() {
        return idNewPersonne;
    }
}
