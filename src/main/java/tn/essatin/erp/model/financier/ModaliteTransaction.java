package tn.essatin.erp.model.financier;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class ModaliteTransaction {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String numero;
    private double montant;
    @Enumerated(EnumType.STRING)
    private ETypeModaliteTransaction type;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDate date;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Enumerated(EnumType.STRING)
    private EStatus status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ManyToOne
    private Transaction transaction;

    public ModaliteTransaction() {
    }

    public ModaliteTransaction(String numero, double montant, ETypeModaliteTransaction type, LocalDate date, EStatus status, Transaction transaction) {
        this.numero = numero;
        this.montant = montant;
        this.type = type;
        this.date = date;
        this.status = status;
        this.transaction = transaction;
    }

    public ModaliteTransaction(Integer id, String numero, double montant, ETypeModaliteTransaction type, LocalDate date, EStatus status, Transaction transaction) {
        this.id = id;
        this.numero = numero;
        this.montant = montant;
        this.type = type;
        this.date = date;
        this.status = status;
        this.transaction = transaction;
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

    public EStatus getStatus() {
        return status;
    }

    public void setStatus(EStatus status) {
        this.status = status;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ModaliteTransaction)) return false;
        ModaliteTransaction that = (ModaliteTransaction) o;
        return Double.compare(that.getMontant(), getMontant()) == 0 && getId().equals(that.getId()) && getNumero().equals(that.getNumero()) && getType() == that.getType() && getDate().equals(that.getDate()) && getStatus() == that.getStatus() && getTransaction().equals(that.getTransaction());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNumero(), getMontant(), getType(), getDate(), getStatus(), getTransaction());
    }

    @Override
    public String toString() {
        return "ModaliteTransaction{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                ", montant=" + montant +
                ", type=" + type +
                ", date=" + date +
                ", status=" + status +
                ", transaction=" + transaction +
                '}';
    }
}
