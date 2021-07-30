package tn.essatin.erp.payload.request.scolarite;
import javax.validation.constraints.Min;

public class FeuilleDEmargement {
    @Min(value = 1, message = "le champ idNiveau est obligatoire")
    int idNiveau ;
    @Min(value = 1, message = "le champ idSession est obligatoire")
    int idSession;
    @Min(value = 1,message = "la variable colonnes ne peut pas etre inferieur a 1" )
    int colones;
    @Min(value = 1,message = "la variable nbre de place ne peut pas etre inferieur a 1")
    int nbrePlace;
    public int getIdNiveau() {
        return idNiveau;
    }

    public int getIdSession() {
        return idSession;
    }

    public int getColones() {
        return colones;
    }
    public int getNbrePlace(){
        return nbrePlace;
    }
}
