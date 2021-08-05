package tn.essatin.erp.payload.request.scolarite;

import javax.validation.constraints.Min;

public class FeuilleDEmargementRequest {
    @Min(value = 1, message = "le champ idNiveau est obligatoire")
    int idNiveau;
    @Min(value = 1, message = "le champ idSession est obligatoire")
    int idSession;
    @Min(value = 1, message = "le champ idSalle est obligatoire")
    int idSalle;


    public int getIdNiveau() {
        return idNiveau;
    }

    public int getIdSession() {
        return idSession;
    }

    public int getIdSalle() {
        return idSalle;
    }

    @Override
    public String toString() {
        return "FeuilleDEmargementRequest{" +
                "idNiveau=" + idNiveau +
                ", idSession=" + idSession +
                ", idSalle=" + idSalle +
                '}';
    }
}
