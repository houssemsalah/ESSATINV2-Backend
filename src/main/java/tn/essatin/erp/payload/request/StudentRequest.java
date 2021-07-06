package tn.essatin.erp.payload.request;

import tn.essatin.erp.model.ContactEtudiant;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class StudentRequest {
    @NotNull
    private int idPersonne;
    private List<ContactEtudiant> contactEtudiants;
    @NotBlank
    private String numeroInscrit;
    @NotBlank
    private String etatInscrit;
    @NotBlank
    private String date;
    @NotBlank
    private List<DiplomeRequest> diplomeRequest;

    public List<ContactEtudiant> getContactEtudiants() {
        return contactEtudiants;
    }

    public void setContactEtudiants(List<ContactEtudiant> contactEtudiants) {
        this.contactEtudiants = contactEtudiants;
    }

    public int getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(int idPersonne) {
        this.idPersonne = idPersonne;
    }

    public List<DiplomeRequest> getDiplomeRequest() {
        return diplomeRequest;
    }

    public void setDiplomeRequest(List<DiplomeRequest> diplomeRequest) {
        this.diplomeRequest = diplomeRequest;
    }

    public String getNumeroInscrit() {
        return numeroInscrit;
    }

    public void setNumeroInscrit(String numeroInscrit) {
        this.numeroInscrit = numeroInscrit;
    }

    public String getEtatInscrit() {
        return etatInscrit;
    }

    public void setEtatInscrit(String etatInscrit) {
        this.etatInscrit = etatInscrit;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
