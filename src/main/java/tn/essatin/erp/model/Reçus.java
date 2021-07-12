package tn.essatin.erp.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Reçus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numero;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateReçu;
    @ManyToOne
    private Etudiants idEtudiant;
    @ManyToOne
    private Cycle idCycle;
    @ManyToOne
    private Parcours idParcours;
    @ManyToOne
    private Niveau idNiveau;

    private Double montant;

    public Reçus() {
    }

    public Reçus(Long numero, LocalDate dateReçu, Etudiants idEtudiant,
                 Cycle idCycle, Parcours idParcours, Niveau idNiveau, Double montant) {
        this.numero = numero;
        this.dateReçu = dateReçu;
        this.idEtudiant = idEtudiant;
        this.idCycle = idCycle;
        this.idParcours = idParcours;
        this.idNiveau = idNiveau;
        this.montant = montant;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public LocalDate getDateReçu() {
        return dateReçu;
    }

    public void setDateReçu(LocalDate dateReçu) {
        this.dateReçu = dateReçu;
    }

    public Etudiants getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(Etudiants idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    public Cycle getIdCycle() {
        return idCycle;
    }

    public void setIdCycle(Cycle idCycle) {
        this.idCycle = idCycle;
    }

    public Parcours getIdParcours() {
        return idParcours;
    }

    public void setIdParcours(Parcours idParcours) {
        this.idParcours = idParcours;
    }

    public Niveau getIdNiveau() {
        return idNiveau;
    }

    public void setIdNiveau(Niveau idNiveau) {
        this.idNiveau = idNiveau;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    @Override
    public String toString() {
        return "Reçus{" +
                "numero=" + numero +
                ", dateReçu=" + dateReçu +
                ", idEtudiant=" + idEtudiant +
                ", idCycle=" + idCycle +
                ", idParcours=" + idParcours +
                ", idNiveau=" + idNiveau +
                ", montant=" + montant +
                '}';
    }
}
