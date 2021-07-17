package tn.essatin.erp.payload.request;

import javax.validation.constraints.Min;

public class SessionUnivRequest {
    @Min(value = 1, message = "la valeur 'idSession' ne peut etre null ou inferieur a 1")
    private Integer idSession;

    public Integer getIdSession() {
        return idSession;
    }
}
