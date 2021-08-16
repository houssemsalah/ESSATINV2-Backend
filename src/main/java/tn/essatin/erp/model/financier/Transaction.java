package tn.essatin.erp.model.financier;

import tn.essatin.erp.model.Personne;
import tn.essatin.erp.model.Session;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;

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
    @Enumerated(EnumType.STRING)
    private EStatus status;

    public Transaction(ETypeTransaction type,
                       LocalDate datePayement, Employer financier, Personne client,
                       Session session, EStatus status) {
        this.type = type;
        this.datePayement = datePayement;
        this.financier = financier;
        this.client = client;
        this.session = session;
        this.status = status;
    }

    public Transaction() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer idTransaction) {
        this.id = idTransaction;
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

    public void setFinancier(Employer idFinancier) {
        this.financier = idFinancier;
    }

    public Personne getClient() {
        return client;
    }

    public void setClient(Personne idClient) {
        this.client = idClient;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public EStatus getStatus() {
        return status;
    }

    public void setStatus(EStatus status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "Transaction{" +
                "idTransaction=" + id +
                ", type=" + type +
                ", datePayement=" + datePayement +
                ", idFinancier=" + financier +
                ", idClient=" + client +
                ", session=" + session +
                ", status=" + status +
                '}';
    }
}
