package tn.essatin.erp.payload.request;


import javax.validation.constraints.Min;

public class SpecialiteRequest {
    @Min(value = 1, message = "la valeur 'idspecialite' ne peut etre null ou inferieur a 1")
    private int idspecialite;

    public Integer getIdspecialite() {
        return idspecialite;
    }
}
