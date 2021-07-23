package tn.essatin.erp.payload.request.scolarite;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ModifierContactEtudiantRequest {

    @Min(value = 1, message = "le champ 'idContact' est obligatoire et doit etre strictement supperieur a 1")
    int idContact;
    @Pattern(regexp = "([0-9]*)", message = "Numero de telephonne ne peut contenir que des chiffres")
    @Size(min = 8, max = 15,message = "Numero de telephonne doit etre entre 8 et 15 chiffres")
    String numero;
    @NotEmpty(message = "Le Nom ne doit pas etre vide")
    String nom;
    @NotEmpty(message = "La designantion ne doit pas etre vide")
    String designation;

    public int getIdContact() {
        return idContact;
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
