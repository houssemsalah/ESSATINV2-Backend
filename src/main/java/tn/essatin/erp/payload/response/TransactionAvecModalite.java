package tn.essatin.erp.payload.response;

import tn.essatin.erp.model.Personne;
import tn.essatin.erp.model.Session;
import tn.essatin.erp.model.financier.ETypeTransaction;
import tn.essatin.erp.model.financier.Employer;
import tn.essatin.erp.model.financier.ModaliteTransaction;
import tn.essatin.erp.model.financier.Transaction;

import java.time.LocalDate;
import java.util.List;

public class TransactionAvecModalite {
    private Integer id;
    private ETypeTransaction type;
    private LocalDate datePayement;
    private Employer financier;
    private Personne client;
    private Session session;
    private List<ModaliteTransaction> modaliteTransactionList;

    public TransactionAvecModalite(Integer id, ETypeTransaction type, LocalDate datePayement, Employer financier, Personne client, Session session, List<ModaliteTransaction> modaliteTransactionList) {
        this.id = id;
        this.type = type;
        this.datePayement = datePayement;
        this.financier = financier;
        this.client = client;
        this.session = session;
        this.modaliteTransactionList = modaliteTransactionList;
    }
    public TransactionAvecModalite(Transaction transaction, List<ModaliteTransaction> modaliteTransactionList) {
        this.id = transaction.getId();
        this.type = transaction.getType();
        this.datePayement = transaction.getDatePayement();
        this.financier = transaction.getFinancier();
        this.client = transaction.getClient();
        this.session = transaction.getSession();
        for(ModaliteTransaction m:modaliteTransactionList)
            m.setTransaction(null);
        this.modaliteTransactionList = modaliteTransactionList;
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

    public List<ModaliteTransaction> getModaliteTransactionList() {
        return modaliteTransactionList;
    }

    public void setModaliteTransactionList(List<ModaliteTransaction> modaliteTransactionList) {
        this.modaliteTransactionList = modaliteTransactionList;
    }

    @Override
    public String toString() {
        return "TransactionAvecModalite{" +
                "id=" + id +
                ", type=" + type +
                ", datePayement=" + datePayement +
                ", financier=" + financier +
                ", client=" + client +
                ", session=" + session +
                ", modaliteTransactionList=" + modaliteTransactionList +
                '}';
    }
}
