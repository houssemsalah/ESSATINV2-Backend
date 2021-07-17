package tn.essatin.erp.payload.request;

import javax.validation.constraints.Min;

public class ParcoursRequest {
    @Min(value = 1, message = "la valeur 'idparcours' ne peut etre null ou inferieur a 1")
    private int idparcours;

    public Integer getIdparcours() {
        return idparcours;
    }

    public void setIdparcours(Integer idparcours) {
        this.idparcours = idparcours;
    }
}
