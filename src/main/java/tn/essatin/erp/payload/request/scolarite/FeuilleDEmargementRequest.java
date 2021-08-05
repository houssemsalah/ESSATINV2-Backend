package tn.essatin.erp.payload.request.scolarite;

import javax.validation.constraints.Min;

public class FeuilleDEmargementRequest {
    @Min(value = 1, message = "le champ idNiveau est obligatoire")
    int idNiveau;
    @Min(value = 1, message = "le champ idSession est obligatoire")
    int idSession;
    @Min(value = 2, message = "la variable nombre de colonnes ne peut pas etre inferieur a 1")
    int nbrecolones;
    @Min(value = 1, message = "la variable nbre de place ne peut pas etre inferieur a 1")
    int nombrePlace;

    public int getIdNiveau() {
        return idNiveau;
    }

    public int getIdSession() {
        return idSession;
    }

    public int getNbrecolones() {
        return nbrecolones;
    }

    public int getNombrePlace() { return nombrePlace; }

    @Override
    public String toString() {
        return "FeuilleDEmargementRequest{" +
                "idNiveau=" + idNiveau +
                ", idSession=" + idSession +
                ", nbrecolones=" + nbrecolones +
                ", nombrePlace=" + nombrePlace +
                '}';
    }
}
