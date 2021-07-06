package tn.essatin.erp.model;

import javax.persistence.*;

@Entity
@Table
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSession;
    private String session;

    public Session() {
    }

    public Session(Integer idSession, String session) {
        this.idSession = idSession;
        this.session = session;
    }

    public Integer getIdSession() {
        return idSession;
    }

    public void setIdSession(Integer idSession) {
        this.idSession = idSession;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    @Override
    public String toString() {
        return "Session{" +
            "idSession=" + idSession +
            ", session='" + session + '\'' +
            '}';
    }
}
