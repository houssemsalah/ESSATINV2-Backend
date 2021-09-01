package tn.essatin.erp.payload.request.scolarite;

import javax.validation.constraints.Min;

public class DepoDeMemoireDeStageRequest {
    @Min(value = 1,message = "idEnregistrement est obligatoire")
    int idEnregistrement;
    String nomGroupe;
    @Min(value = 1,message = "idSignataire est obligatoire")
    int idSignataire;

    public DepoDeMemoireDeStageRequest(int idEnregistrement, String nomGroupe, int idSignataire) {
        this.idEnregistrement = idEnregistrement;
        this.nomGroupe = nomGroupe;
        this.idSignataire = idSignataire;
    }

    public int getIdEnregistrement() {
        return idEnregistrement;
    }

    public String getNomGroupe() {
        return nomGroupe;
    }

    public int getIdSignataire() {
        return idSignataire;
    }
}
