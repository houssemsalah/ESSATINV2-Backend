package tn.essatin.erp.payload.request.scolarite;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CertificatRequest {
    @Min(value = 1, message = "le champ idEnregistrement est obligatoire")
    int idEnregistrement;
    @NotNull(message = "le champ entete est obligatoire")
    boolean entete;
    @Min(value = 1,message = "le signataire est obligatoire")
    int idSignataire;

    public CertificatRequest(int idEnregistrement, boolean entete, int idSignataire) {
        this.idEnregistrement = idEnregistrement;
        this.entete = entete;
        this.idSignataire = idSignataire;
    }

    public int getIdEnregistrement() {
        return idEnregistrement;
    }

    public boolean isEntete() {
        return entete;
    }

    public int getIdSignataire() {
        return idSignataire;
    }
}
