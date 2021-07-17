package tn.essatin.erp.payload.request;

import tn.essatin.erp.model.ContactEtudiant;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.List;

public class StudentRequest {
    @Min(value = 1, message = "la valeur 'idPersonne' ne peut etre null ou inferieur a 1")
    private int idPersonne;
    @NotNull(message = "une list de ContactEtudiant doit etre fourni ")
    private List<ContactEtudiant> contactEtudiants;
    @NotBlank(message = "numeroInscrit ne peut etre vide")
    private String numeroInscrit;
    @NotBlank(message = "etatInscrit ne peut etre vide")
    private String etatInscrit;
    @Past(message = "la date de naissance doit etre au passe")
    private String date;
    @NotNull(message = "une list de DiplomeRequest doit etre fourni ")
    private List<DiplomeRequest> diplomeRequest;

    public List<ContactEtudiant> getContactEtudiants() {
        return contactEtudiants;
    }

    public int getIdPersonne() {
        return idPersonne;
    }

    public List<DiplomeRequest> getDiplomeRequest() {
        return diplomeRequest;
    }

    public String getNumeroInscrit() {
        return numeroInscrit;
    }

    public String getEtatInscrit() {
        return etatInscrit;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
