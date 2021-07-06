package tn.essatin.erp.payload.request;

import javax.validation.constraints.NotBlank;

public class IdentificateurRequest {
    @NotBlank
    private String numidentificateur;

    public String getNumidentificateur() {
        return numidentificateur;
    }

    public void setNumidentificateur(String numidentificateur) {
        this.numidentificateur = numidentificateur;
    }
}
