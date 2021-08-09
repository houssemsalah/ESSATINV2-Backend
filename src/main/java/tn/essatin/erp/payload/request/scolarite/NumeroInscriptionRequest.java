package tn.essatin.erp.payload.request.scolarite;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class NumeroInscriptionRequest {
    @NotEmpty
    private String numeroInscription;

    public String getNumeroInscription() {
        return numeroInscription;
    }
}
