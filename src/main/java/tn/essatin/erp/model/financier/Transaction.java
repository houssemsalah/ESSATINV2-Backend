package tn.essatin.erp.model.financier;

import tn.essatin.erp.model.Personne;
import tn.essatin.erp.model.Session;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private ETypeTransaction type;
    private LocalDate datePayement;
    @ManyToOne
    private Employer financier;
    @ManyToOne
    private Personne client;
    @ManyToOne
    private Session session;

    public Transaction(Integer id, ETypeTransaction type, LocalDate datePayement, Employer financier, Personne client, Session session) {
        this.id = id;
        this.type = type;
        this.datePayement = datePayement;
        this.financier = financier;
        this.client = client;
        this.session = session;
    }

    public Transaction(ETypeTransaction type, LocalDate datePayement, Employer financier, Personne client, Session session) {
        this.type = type;
        this.datePayement = datePayement;
        this.financier = financier;
        this.client = client;
        this.session = session;
    }

    public Transaction() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ETypeTransaction getType() {
        return type;
    }

    public void setType(ETypeTransaction type) {
        this.type = type;
    }

    public LocalDate getDatePayement() {
        return datePayement;
    }

    public void setDatePayement(LocalDate datePayement) {
        this.datePayement = datePayement;
    }

    public Employer getFinancier() {
        return financier;
    }

    public void setFinancier(Employer financier) {
        this.financier = financier;
    }

    public Personne getClient() {
        return client;
    }

    public void setClient(Personne client) {
        this.client = client;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;
        Transaction that = (Transaction) o;
        return getId().equals(that.getId()) && getType() == that.getType() && getDatePayement().equals(that.getDatePayement()) && getFinancier().equals(that.getFinancier()) && getClient().equals(that.getClient()) && getSession().equals(that.getSession());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getType(), getDatePayement(), getFinancier(), getClient(), getSession());
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", type=" + type +
                ", datePayement=" + datePayement +
                ", financier=" + financier +
                ", client=" + client +
                ", session=" + session +
                '}';
    }
}
