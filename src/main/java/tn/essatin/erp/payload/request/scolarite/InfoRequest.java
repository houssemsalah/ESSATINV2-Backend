package tn.essatin.erp.payload.request.scolarite;

import javax.validation.constraints.Min;

public class InfoRequest {
    @Min(value = 1, message = "la variable 'idEnregistrement' doit avoir une valeur strictement supperieur a 0")
    int idEnregistrement;
    boolean entete;

    public int getIdEnregistrement() {
        return idEnregistrement;
    }

    public boolean isEntete() {
        return entete;
    }
}
