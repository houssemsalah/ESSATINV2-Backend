package tn.essatin.erp.payload.request.scolarite;

import javax.validation.constraints.Min;

public class ModifierInscriptionRequest {
    @Min(value = 1, message = "le champ 'idInscription' est obligatoire et doit etre strictement supperieur a 1")
    int idInscription;
    @Min(value = 1, message = "le champ 'idNiveaux' est obligatoire et doit etre strictement supperieur a 1")
    int idNiveaux;

    public int getIdInscription() {
        return idInscription;
    }

    public int getIdNiveaux() {
        return idNiveaux;
    }
}
