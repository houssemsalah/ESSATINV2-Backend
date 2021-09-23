package tn.essatin.erp.payload.request.scolarite;

import tn.essatin.erp.model.Scolarite.ContactEtudiant;
import tn.essatin.erp.model.Scolarite.DiplomeEtudiant;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

public class PreInscriptionRequest {
    @NotBlank(message = "Le nom est obligatoire")
    String nom;
    @NotBlank(message = "Le prenom est obligatoire")
    String prenom;
    @Min(value = 1, message = "le champ idTypeIdentificateur est obligatoire")
    int idTypeIdentificateur;
    @Size(min = 8, max = 9, message = "Numero ididentif invalide")
    String ididentif;
    @Min(value = 1, message = "le champ niveauxInscrit est obligatoire")
    int niveauxInscrit;

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public int getIdTypeIdentificateur() {
        return idTypeIdentificateur;
    }

    public String getIdidentif() {
        return ididentif;
    }

    public int getNiveauxInscrit() {
        return niveauxInscrit;
    }
}
