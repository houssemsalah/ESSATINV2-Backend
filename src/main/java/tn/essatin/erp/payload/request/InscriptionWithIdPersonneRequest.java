package tn.essatin.erp.payload.request;

import javax.validation.constraints.NotNull;

public class InscriptionWithIdPersonneRequest {
    @NotNull
    int niveauxInscrit;
    @NotNull
    int idPersonne;

    public int getNiveauxInscrit() {
        return niveauxInscrit;
    }

    public void setNiveauxInscrit(int niveauxInscrit) {
        this.niveauxInscrit = niveauxInscrit;
    }

    public int getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(int idPersonne) {
        this.idPersonne = idPersonne;
    }
}
