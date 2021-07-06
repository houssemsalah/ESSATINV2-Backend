package tn.essatin.erp.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTransaction;
    private String type;
    @OneToMany
    private Collection<ModaliteTransaction> modalite;
    private LocalDate datePayement;
    @OneToOne
    private Employer idFinancier;
    @OneToOne
    private Personne idClient;
    @OneToOne
    private Session session;
    private String status;
    private float montant;

    public Transaction(String type, Collection<ModaliteTransaction> modalite,
                       LocalDate datePayement, Employer idFinancier, Personne idClient,
                       Session session, String status, Float montant) {
        this.type = type;
        this.modalite = modalite;
        this.datePayement = datePayement;
        this.idFinancier = idFinancier;
        this.idClient = idClient;
        this.session = session;
        this.status = status;
        this.montant = montant;
    }

    public Transaction() {
    }

    public Integer getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(Integer idTransaction) {
        this.idTransaction = idTransaction;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Collection<ModaliteTransaction> getModalite() {
        return modalite;
    }

    public void setModalite(Collection<ModaliteTransaction> modalite) {
        this.modalite = modalite;
    }

    public LocalDate getDatePayement() {
        return datePayement;
    }

    public void setDatePayement(LocalDate datePayement) {
        this.datePayement = datePayement;
    }

    public Employer getIdFinancier() {
        return idFinancier;
    }

    public void setIdFinancier(Employer idFinancier) {
        this.idFinancier = idFinancier;
    }

    public Personne getIdClient() {
        return idClient;
    }

    public void setIdClient(Personne idClient) {
        this.idClient = idClient;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    @Override
    public String toString() {
        return "Transaction{" +
            "idTransaction=" + idTransaction +
            ", type=" + type +
            ", modalite=" + modalite +
            ", datePayement=" + datePayement +
            ", idFinancier=" + idFinancier +
            ", idClient=" + idClient +
            ", session=" + session +
            ", status=" + status +
            ", montant=" + montant +
            '}';
    }
}
