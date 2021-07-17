package tn.essatin.erp.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class InscriptionWithIdPersonneRequest {
    @Min(value = 1, message = "le champ niveauxInscrit est obligatoire")
    int niveauxInscrit;
    @Min(value = 1, message = "le champ idPersonne est obligatoire")
    int idPersonne;
    @Email(message = "l'Email doit etre valid")
    String mail;
    @NotBlank(message = "L'addresse est obligatoire")
    String adresse;
    @Pattern(regexp = "(^$|[0-9]{15})", message = "Numero de telephonne invalide")
    String tel;

    public int getNiveauxInscrit() {
        return niveauxInscrit;
    }

    public int getIdPersonne() {
        return idPersonne;
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
