package tn.essatin.erp.payload.request;

import javax.validation.constraints.NotNull;

public class InfoRequest {
    @NotNull
    int idEnregistrement;

    public int getIdEnregistrement() {
        return idEnregistrement;
    }

    public void setIdEnregistrement(int idEnregistrement) {
        this.idEnregistrement = idEnregistrement;
    }
}
