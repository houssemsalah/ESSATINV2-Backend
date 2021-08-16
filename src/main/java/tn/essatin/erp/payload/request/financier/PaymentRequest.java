package tn.essatin.erp.payload.request.financier;

import tn.essatin.erp.model.financier.ModaliteTransaction;

import javax.validation.constraints.NotNull;
import java.util.Set;

public class PaymentRequest {
    @NotNull
    private int personne;
    @NotNull
    private int session;
    @NotNull
    private Set<ModaliteTransaction> modaliteTransactionSet;
    @NotNull
    private int idFinancier;
    @NotNull
    private String statusTransaction;

    public int getIdFinancier() {
        return idFinancier;
    }

    public void setIdFinancier(int idFinancier) {
        this.idFinancier = idFinancier;
    }

    public int getPersonne() {
        return personne;
    }

    public void setPersonne(int personne) {
        this.personne = personne;
    }

    public int getSession() {
        return session;
    }

    public void setSession(int session) {
        this.session = session;
    }

    public String getStatusTransaction() {
        return statusTransaction;
    }

    public void setStatusTransaction(String statusTransaction) {
        this.statusTransaction = statusTransaction;
    }

    public Set<ModaliteTransaction> getModaliteTransactionSet() {
        return modaliteTransactionSet;
    }

    public void setModaliteTransactionSet(Set<ModaliteTransaction> modaliteTransactionSet) {
        this.modaliteTransactionSet = modaliteTransactionSet;
    }
}
