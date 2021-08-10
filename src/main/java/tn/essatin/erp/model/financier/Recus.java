package tn.essatin.erp.model.financier;

import org.springframework.format.annotation.DateTimeFormat;
import tn.essatin.erp.model.Scolarite.Cycle;
import tn.essatin.erp.model.Scolarite.Etudiants;
import tn.essatin.erp.model.Scolarite.Niveau;
import tn.essatin.erp.model.Scolarite.Parcours;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Recus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numero;
    @OneToOne
    private Transaction transaction;
    public Recus() {
    }

    public Recus(Transaction transaction) {
        this.transaction = transaction;
    }

    public Recus(Long numero, Transaction transaction) {
        this.numero = numero;
        this.transaction = transaction;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public String toString() {
        return "Recus{" +
                "numero=" + numero +
                ", transaction=" + transaction +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Recus)) return false;
        Recus recus = (Recus) o;
        return getNumero().equals(recus.getNumero()) &&
                getTransaction().equals(recus.getTransaction());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumero(), getTransaction());
    }
}
