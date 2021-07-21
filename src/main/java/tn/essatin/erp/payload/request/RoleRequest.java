package tn.essatin.erp.payload.request;

import javax.validation.constraints.Min;

public class RoleRequest {
    @Min(value = 1, message = "la valeur 'idCompte' ne peut etre null ou inferieur a 1")
    int idCompte;

    public Integer getIdCompte() {
        return idCompte;
    }

}
