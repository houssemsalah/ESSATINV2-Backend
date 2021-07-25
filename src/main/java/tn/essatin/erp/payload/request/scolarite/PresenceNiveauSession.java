package tn.essatin.erp.payload.request.scolarite;

import javax.validation.constraints.Min;

public class PresenceNiveauSession {
    @Min(value = 1, message = "le champ idNiveau est obligatoire")
    int idNiveau ;
    @Min(value = 1, message = "le champ idSession est obligatoire")
    int idSession;

    public int getIdNiveau() {
        return idNiveau;
    }

    public int getIdSession() {
        return idSession;
    }
}
