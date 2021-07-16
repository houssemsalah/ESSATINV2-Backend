package tn.essatin.erp.payload.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class InscriptionWithIdPersonneRequest {
    @NotNull
    int niveauxInscrit;
    @NotNull
    int idPersonne;
    @NotEmpty
     String mail;
    @NotEmpty
     String adresse;
    @NotEmpty
     String tel;

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

    public String getMail() {
        return mail;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getTel() {
        return tel;
    }
}
