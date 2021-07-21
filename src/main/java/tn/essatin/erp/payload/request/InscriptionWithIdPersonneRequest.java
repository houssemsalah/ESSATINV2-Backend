package tn.essatin.erp.payload.request;

import javax.validation.constraints.*;

public class InscriptionWithIdPersonneRequest {
    @Min(value = 1, message = "le champ niveauxInscrit est obligatoire")
    int niveauxInscrit;
    @Min(value = 1, message = "le champ idPersonne est obligatoire")
    int idPersonne;
    @Email(message = "l'Email doit etre valid")
    String mail;
    @NotBlank(message = "L'addresse est obligatoire")
    String adresse;
    @Pattern(regexp = "([0-9]*)", message = "Numero de telephonne ne peut contenir que des chiffres")
    @Size(min = 8, max = 15,message = "Numero de telephonne doit etre entre 8 et 15 chiffres")
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
