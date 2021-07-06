package tn.essatin.erp.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Enregistrement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEnregistrement;
    @ManyToOne
    private Inscription idInscription;
    @ManyToOne
    private Niveau idNiveau;
    @ManyToOne
    private Session idSession;
    private LocalDate dateEnregistrement;
    private int etatFinanciere;

    public Enregistrement(Inscription idInscription, Niveau idNiveau, Session idSession, LocalDate dateEnregistrement, int etatFinanciere) {
        this.idInscription = idInscription;
        this.idNiveau = idNiveau;
        this.idSession = idSession;
        this.dateEnregistrement = dateEnregistrement;
        this.etatFinanciere = etatFinanciere;
    }

    public Enregistrement() {
    }

    public Integer getIdEnregistrement() {
        return idEnregistrement;
    }

    public void setIdEnregistrement(Integer idEnregistrement) {
        this.idEnregistrement = idEnregistrement;
    }

    public Inscription getIdInscription() {
        return idInscription;
    }

    public void setIdInscription(Inscription idInscription) {
        this.idInscription = idInscription;
    }

    public Niveau getIdNiveau() {
        return idNiveau;
    }

    public void setIdNiveau(Niveau idNiveau) {
        this.idNiveau = idNiveau;
    }

    public Session getIdSession() {
        return idSession;
    }

    public void setIdSession(Session idSession) {
        this.idSession = idSession;
    }

    public LocalDate getDateEnregistrement() {
        return dateEnregistrement;
    }

    public void setDateEnregistrement(LocalDate dateEnregistrement) {
        this.dateEnregistrement = dateEnregistrement;
    }

    public int getEtatFinanciere() {
        return etatFinanciere;
    }

    public void setEtatFinanciere(int etatFinanciere) {
        this.etatFinanciere = etatFinanciere;
    }

    @Override
    public String toString() {
        return "Enregistrement{" +
            "idEnregistrement=" + idEnregistrement +
            ", idInscription=" + idInscription +
            ", idNiveau=" + idNiveau +
            ", idSession=" + idSession +
            ", dateEnregistrement=" + dateEnregistrement +
            ", etatFinanciere=" + etatFinanciere +
            '}';
    }
}
