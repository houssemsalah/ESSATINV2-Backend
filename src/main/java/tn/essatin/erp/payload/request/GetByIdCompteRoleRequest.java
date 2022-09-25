package tn.essatin.erp.payload.request;

import javax.validation.constraints.Min;

public class GetByIdCompteRoleRequest {
    @Min(value = 1, message = "la variable 'idCompte' doit avoir une valeur strictement supperieur a 0")
    int idCompte ;   @Min(value = 1, message = "la variable 'idRole' doit avoir une valeur strictement supperieur a 0")
    int idRole;

    public int getIdCompte() {
        return idCompte;
    }

    public int getIdRole() {
        return idRole;
    }
}
