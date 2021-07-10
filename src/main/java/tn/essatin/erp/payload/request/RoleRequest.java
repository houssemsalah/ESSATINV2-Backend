package tn.essatin.erp.payload.request;

import javax.validation.constraints.NotNull;

public class RoleRequest {
    @NotNull
    Integer idCompte;

    public Integer getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(Integer idCompte) {
        this.idCompte = idCompte;
    }
}
