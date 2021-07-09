package tn.essatin.erp.payload.request;

import javax.validation.constraints.NotNull;

public class CertificatRequest {
    @NotNull
    int idEnregistrement;
    @NotNull
    boolean directeur;

    public int getIdEnregistrement() {
        return idEnregistrement;
    }

    public void setIdEnregistrement(int idEnregistrement) {
        this.idEnregistrement = idEnregistrement;
    }

    public boolean isDirecteur() {
        return directeur;
    }

    public void setDirecteur(boolean directeur) {
        this.directeur = directeur;
    }
}
