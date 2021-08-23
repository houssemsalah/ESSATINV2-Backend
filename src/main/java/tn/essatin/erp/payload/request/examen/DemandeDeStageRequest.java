package tn.essatin.erp.payload.request.examen;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class DemandeDeStageRequest {

    @Min(value = 1, message = "la variable idenregistrement ne peut pas etre inferieur a 1")
    int idenregistrement;
    @NotNull(message = "le nom de la societé ne peut etre vide")
    @NotBlank(message = "le nom de la societé ne peut etre vide")
    String nomSociete;
    @Min(value = 1, message = "la variable numCase ne peut pas etre inferieur a 1")
    @Max(value = 3, message = "la variable numCase ne peut pas etre superieur a 3")
    int numCase;
    String designantionEntreprise ;
    @NotNull
    int idSignataire;
    LocalDate date;


    public DemandeDeStageRequest(int idenregistrement, String nomSociete, int numCase,
                                 String designantionEntreprise,int idSignataire, LocalDate date) {
        this.idenregistrement = idenregistrement;
        this.nomSociete = nomSociete;
        this.numCase = numCase;
        this.designantionEntreprise = designantionEntreprise;
        this.idSignataire=idSignataire;
        this.date=date;
    }

    public int getIdenregistrement() {
        return idenregistrement;
    }

    public String getNomSociete() {
        return nomSociete;
    }

    public int getNumCase() {
        return numCase;
    }

    public String getDesignantionEntreprise() {
        return designantionEntreprise;
    }

    public int getIdSignataire() {
        return idSignataire;
    }

    public LocalDate getDate() {
        return date;
    }
}
