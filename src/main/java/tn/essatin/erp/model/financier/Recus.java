package tn.essatin.erp.model.financier;

import org.springframework.format.annotation.DateTimeFormat;
import tn.essatin.erp.model.Scolarite.Cycle;
import tn.essatin.erp.model.Scolarite.Etudiants;
import tn.essatin.erp.model.Scolarite.Niveau;
import tn.essatin.erp.model.Scolarite.Parcours;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Recus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numero;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateRecu;
    @ManyToOne
    private Etudiants idEtudiant;
    @ManyToOne
    private Cycle idCycle;
    @ManyToOne
    private Parcours idParcours;
    @ManyToOne
    private Niveau idNiveau;

    private Double montant;

    public Recus() {
    }

    public Recus(Long numero, LocalDate dateRecu, Etudiants idEtudiant,
                 Cycle idCycle, Parcours idParcours, Niveau idNiveau, Double montant) {
        this.numero = numero;
        this.dateRecu = dateRecu;
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

    public LocalDate getDateRecu() {
        return dateRecu;
    }

    public void setDateRecu(LocalDate dateRecu) {
        this.dateRecu = dateRecu;
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
                ", dateReçu=" + dateRecu +
                ", idEtudiant=" + idEtudiant +
                ", idCycle=" + idCycle +
                ", idParcours=" + idParcours +
                ", idNiveau=" + idNiveau +
                ", montant=" + montant +
                '}';
    }
}
