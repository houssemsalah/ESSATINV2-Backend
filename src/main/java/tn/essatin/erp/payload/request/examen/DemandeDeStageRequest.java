package tn.essatin.erp.payload.request.examen;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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


    public DemandeDeStageRequest(int idenregistrement, String nomSociete, int numCase, String designantionEntreprise) {
        this.idenregistrement = idenregistrement;
        this.nomSociete = nomSociete;
        this.numCase = numCase;
        this.designantionEntreprise = designantionEntreprise;
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
}
