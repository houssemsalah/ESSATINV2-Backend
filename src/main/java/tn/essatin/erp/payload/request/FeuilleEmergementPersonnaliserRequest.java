package tn.essatin.erp.payload.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

public class FeuilleEmergementPersonnaliserRequest {

    @Min(value = 2, message = "la variable nombre de colonnes ne peut pas etre inferieur a 1")
    int nbrecolones;
    @Min(value = 1, message = "la variable nbre de place ne peut pas etre inferieur a 1")
    int nombrePlace;
    @NotNull
    List<Integer[]> idEnregistrements;


    public int getNbrecolones() {
        return nbrecolones;
    }

    public int getNombrePlace() {
        return nombrePlace;
    }

    public List<Integer[]> getIdEnregistrements() {
        return idEnregistrements;
    }
}
