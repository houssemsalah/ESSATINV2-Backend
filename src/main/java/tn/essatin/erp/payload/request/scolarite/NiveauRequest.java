package tn.essatin.erp.payload.request.scolarite;

import javax.validation.constraints.Min;

public class NiveauRequest {
    @Min(value = 1, message = "la valeur 'idNiveau' ne peut etre null ou inferieur a 1")
    private int idNiveau;

    public int getIdNiveau() {
        return idNiveau;
    }

    public void setIdNiveau(int idNiveau) {
        this.idNiveau = idNiveau;
    }

}
