package tn.essatin.erp.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Inscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idInscription;
    @OneToOne
    private Etudiants idEtudiant;
    private String numeroInscription;
    private LocalDate date;
    @OneToOne
    private EtatInscription idEtatInscription;

    public Inscription(Etudiants idEtudiant, String numeroInscription, LocalDate date,
                       EtatInscription idEtatInscription) {
        this.idEtudiant = idEtudiant;
        this.numeroInscription = numeroInscription;
        this.date = date;
        this.idEtatInscription = idEtatInscription;
    }

    public Inscription() {
    }

    public Integer getIdInscription() {
        return idInscription;
    }

    public void setIdInscription(Integer idInscription) {
        this.idInscription = idInscription;
    }

    public Etudiants getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(Etudiants idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    public String getNumeroInscription() {
        return numeroInscription;
    }

    public void setNumeroInscription(String numeroInscription) {
        this.numeroInscription = numeroInscription;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public EtatInscription getIdEtatInscription() {
        return idEtatInscription;
    }

    public void setIdEtatInscription(EtatInscription idEtatInscription) {
        this.idEtatInscription = idEtatInscription;
    }

    @Override
    public String toString() {
        return "Inscription{" +
                "idInscription=" + idInscription +
                ", idEtudiant=" + idEtudiant +
                ", numeroInscription='" + numeroInscription + '\'' +
                ", date=" + date +
                ", idEtatInscription=" + idEtatInscription +
                '}';
    }
}
