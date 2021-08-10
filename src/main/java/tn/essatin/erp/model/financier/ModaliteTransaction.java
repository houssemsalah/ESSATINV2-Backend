package tn.essatin.erp.model.financier;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class ModaliteTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String numero;
    private double montant;
    @Enumerated(EnumType.STRING)
    private ETypeModaliteTransaction type;
    private LocalDate date;

    public ModaliteTransaction(String numero, double montant, ETypeModaliteTransaction type, LocalDate date) {
        this.numero = numero;
        this.montant = montant;
        this.type = type;
        this.date = date;
    }

    public ModaliteTransaction() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public ETypeModaliteTransaction getType() {
        return type;
    }

    public void setType(ETypeModaliteTransaction type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ModaliteTransaction{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                ", montant=" + montant +
                ", type='" + type + '\'' +
                ", date=" + date +
                '}';
    }
}
