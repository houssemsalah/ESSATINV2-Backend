package tn.essatin.erp.payload.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

public class FeuilleEmergementPersonnaliserRequest {


    @Min(value = 1, message = "la variable nbre de place ne peut pas etre inferieur a 1")
    int idSalle;
    @NotNull
    List<Integer[]> idEnregistrements;


    public int getIdSalle() {
        return idSalle;
    }

    public List<Integer[]> getIdEnregistrements() {
        return idEnregistrements;
    }
}
