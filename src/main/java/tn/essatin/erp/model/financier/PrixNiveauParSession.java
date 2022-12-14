package tn.essatin.erp.model.financier;

import tn.essatin.erp.model.Scolarite.Niveau;
import tn.essatin.erp.model.Session;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class PrixNiveauParSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne(cascade = CascadeType.ALL)
    private Niveau niveau;
    @OneToOne(cascade = CascadeType.ALL)
    private Session session;
    private double montantNiveau;
    private LocalDate date;

    public PrixNiveauParSession(Niveau niveau, Session session, double montantNiveau, LocalDate date) {
        this.niveau = niveau;
        this.session = session;
        this.montantNiveau = montantNiveau;
        this.date = date;
    }

    public PrixNiveauParSession() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Niveau getNiveau() {
        return niveau;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public double getMontantNiveau() {
        return montantNiveau;
    }

    public void setMontantNiveau(double montantNiveau) {
        this.montantNiveau = montantNiveau;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "PrixNiveauParSession{" +
            "id=" + id +
            ", niveau=" + niveau +
            ", session=" + session +
            ", montantNiveau=" + montantNiveau +
            ", date=" + date +
            '}';
    }
}
