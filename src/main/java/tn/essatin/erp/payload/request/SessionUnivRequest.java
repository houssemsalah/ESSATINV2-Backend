package tn.essatin.erp.payload.request;

import javax.validation.constraints.NotNull;

public class SessionUnivRequest {
    @NotNull
    private  Integer idSession;

    public Integer getIdSession() {
        return idSession;
    }

    public void setIdSession(Integer idSession) {
        this.idSession = idSession;
    }
}
