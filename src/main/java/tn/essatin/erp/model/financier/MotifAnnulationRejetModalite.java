package tn.essatin.erp.model.financier;


import com.fasterxml.jackson.annotation.JsonInclude;
import tn.essatin.erp.model.Compte;
import tn.essatin.erp.model.Personne;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class MotifAnnulationRejetModalite {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    Personne personneFinancier;
    LocalDate date;
    @ManyToOne
    ModaliteTransaction modaliteTransaction;
    @Column(length = 5000)
    String motife;

    public MotifAnnulationRejetModalite() {
    }

    public MotifAnnulationRejetModalite(Personne personneFinancier, LocalDate date, ModaliteTransaction modaliteTransaction, String motife) {
        this.personneFinancier = personneFinancier;
        this.date = date;
        this.modaliteTransaction = modaliteTransaction;
        this.motife = motife;
    }

    public MotifAnnulationRejetModalite(Integer id, Personne personneFinancier, LocalDate date, ModaliteTransaction modaliteTransaction, String motife) {
        this.id = id;
        this.personneFinancier = personneFinancier;
        this.date = date;
        this.modaliteTransaction = modaliteTransaction;
        this.motife = motife;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Personne getPersonneFinancier() {
        return personneFinancier;
    }

    public void setPersonneFinancier(Personne personneFinancier) {
        this.personneFinancier = personneFinancier;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ModaliteTransaction getModaliteTransaction() {
        return modaliteTransaction;
    }

    public void setModaliteTransaction(ModaliteTransaction modaliteTransaction) {
        this.modaliteTransaction = modaliteTransaction;
    }

    public String getMotife() {
        return motife;
    }

    public void setMotife(String motife) {
        this.motife = motife;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MotifAnnulationRejetModalite)) return false;
        MotifAnnulationRejetModalite that = (MotifAnnulationRejetModalite) o;
        return getId().equals(that.getId()) && getPersonneFinancier().equals(that.getPersonneFinancier()) && getDate().equals(that.getDate()) && getModaliteTransaction().equals(that.getModaliteTransaction()) && getMotife().equals(that.getMotife());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPersonneFinancier(), getDate(), getModaliteTransaction(), getMotife());
    }

    @Override
    public String toString() {
        return "MotifAnnulationRejetModalite{" +
                "id=" + id +
                ", personneFinancier=" + personneFinancier +
                ", date=" + date +
                ", modaliteTransaction=" + modaliteTransaction +
                ", motife='" + motife + '\'' +
                '}';
    }
}
