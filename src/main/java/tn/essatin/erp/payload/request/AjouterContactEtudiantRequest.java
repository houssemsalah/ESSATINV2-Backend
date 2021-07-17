package tn.essatin.erp.payload.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class AjouterContactEtudiantRequest {
    @Min(value = 1, message = "le champ idEtudiant doit etre present et strictement supperieur a zero")
    int idEtudiant;
    @Pattern(regexp="(^$|[0-9]{15})",message = "Numero de telephonne invalide")
    String numero;
    @NotEmpty(message = "le nom ne peut pas etre vide")
    String nom;
    @NotEmpty(message = "la designation ne peut pas etre vide")
    String designation;

    public int getIdEtudiant() {
        return idEtudiant;
    }

    public String getNumero() {
        return numero;
    }

    public String getNom() {
        return nom;
    }

    public String getDesignation() {
        return designation;
    }
}
