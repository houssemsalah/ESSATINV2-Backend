package tn.essatin.erp.payload.request.scolarite;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class FeuilleDeNote {
    @Min(value = 1, message = "le champ idNiveau est obligatoire")
    int idNiveau ;
    @Min(value = 1, message = "le champ idSession est obligatoire")
    int idSession;
    @Min(value = 1,message = "la variable colonnes ne peut pas etre inferieur a 1" )
    @Max(value = 16,message = "la variable colonnes ne peut pas etre superieur a 16")
    int colones;

    public FeuilleDeNote(int idNiveau, int idSession, int colones) {
        this.idNiveau = idNiveau;
        this.idSession = idSession;
        this.colones = colones;
    }

    public int getIdNiveau() {
        return idNiveau;
    }

    public int getIdSession() {
        return idSession;
    }

    public int getColones() {
        return colones;
    }
}
