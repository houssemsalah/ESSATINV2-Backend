package tn.essatin.erp.payload.request.examen;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class AttestationDeReussiteRequest {


    @Min(value = 1,message = "id enregitrement doit etre superieur à zero")
    int idEnregitrement;
    @NotNull(message = "le type de session ne peut etre vide")
    @NotBlank(message = "le type de session ne peut etre vide")
    String typeSession;
    @Range(min=1,max = 5,message = "la variable numMention doit etre comprise entre 1 et 5")
    int numMention;


    public AttestationDeReussiteRequest(int idEnregitrement, String typeSession, int numMention) {
        this.idEnregitrement = idEnregitrement;
        this.typeSession = typeSession;
        this.numMention = numMention;
    }
    public int getIdEnregitrement() {
        return idEnregitrement;
    }

    public String getTypeSession() {
        return typeSession;
    }

    public int getNumMention() {
        return numMention;
    }

}
