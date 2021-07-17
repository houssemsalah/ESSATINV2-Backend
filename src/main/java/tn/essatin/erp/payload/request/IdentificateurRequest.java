package tn.essatin.erp.payload.request;

import javax.validation.constraints.Size;

public class IdentificateurRequest {
    @Size(min = 8, max = 9, message = "Numero identificateur invalide")
    private String numidentificateur;

    public String getNumidentificateur() {

        return numidentificateur;
    }


}
