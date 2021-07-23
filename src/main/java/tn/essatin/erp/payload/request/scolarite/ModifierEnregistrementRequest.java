package tn.essatin.erp.payload.request.scolarite;

import javax.validation.constraints.Min;

public class ModifierEnregistrementRequest {
    @Min(value = 1, message = "le champ 'idEnregistrement' est obligatoire et doit etre strictement supperieur a 1")
    int idEnregistrement;
    @Min(value = 1, message = "le champ 'idNiveaux' est obligatoire et doit etre strictement supperieur a 1")
    int idNiveaux;

    public int getIdEnregistrement() {
        return idEnregistrement;
    }

    public int getIdNiveaux() {
        return idNiveaux;
    }
}
