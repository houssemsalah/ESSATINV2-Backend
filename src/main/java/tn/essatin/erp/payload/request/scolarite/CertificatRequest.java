package tn.essatin.erp.payload.request.scolarite;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CertificatRequest {
    @Min(value = 1, message = "le champ idEnregistrement est obligatoire")
    int idEnregistrement;
    @NotNull(message = "le champ directeur est obligatoire")
    boolean directeur;

    public int getIdEnregistrement() {
        return idEnregistrement;
    }

    public boolean isDirecteur() {
        return directeur;
    }

}
