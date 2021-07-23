package tn.essatin.erp.payload.request.scolarite;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class AjouterContactEtudiantRequest {
    @Min(value = 1, message = "le champ idEtudiant doit etre present et strictement supperieur a zero")
    int idEtudiant;
    @Pattern(regexp = "([0-9]*)", message = "Numero de telephonne ne peut contenir que des chiffres")
    @Size(min = 8, max = 15,message = "Numero de telephonne doit etre entre 8 et 15 chiffres")
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
