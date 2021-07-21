package tn.essatin.erp.payload.request;

import javax.validation.constraints.Min;

public class NivSessRequest {
    @Min(value = 1, message = "le champ idNiveaux est obligatoire")
    private int idNiveaux;
    @Min(value = 1, message = "le champ idSession est obligatoire")
    private int idSession;

    public Integer getIdNiveaux() {
        return idNiveaux;
    }

    public Integer getIdSession() {
        return idSession;
    }
}
