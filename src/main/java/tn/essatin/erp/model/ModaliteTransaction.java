package tn.essatin.erp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class ModaliteTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String numero;
    private float montant;
    private String type;
    private LocalDate date;

    public ModaliteTransaction(String numero, float montant, String type, LocalDate date) {
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

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
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
