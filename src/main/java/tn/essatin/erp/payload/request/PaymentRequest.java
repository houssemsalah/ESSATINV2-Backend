package tn.essatin.erp.payload.request;

import tn.essatin.erp.model.ModaliteTransaction;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

public class PaymentRequest {
    @NotNull
    private int personne;
    @NotNull
    private float montant;
    @NotNull
    private int session;
    @NotBlank
    private String datePayement;
    @NotNull
    private int typeTransaction;
    @NotNull
    private Set<ModaliteTransaction> modaliteTransactionSet;
    @NotNull
    private int idFinancier;
    @NotNull
    private int statusTransaction;

    public int getTypeTransaction() {
        return typeTransaction;
    }

    public void setTypeTransaction(int typeTransaction) {
        this.typeTransaction = typeTransaction;
    }

    public String getDatePayement() {
        return datePayement;
    }

    public void setDatePayement(String datePayement) {
        this.datePayement = datePayement;
    }

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

    public int getStatusTransaction() {
        return statusTransaction;
    }

    public void setStatusTransaction(int statusTransaction) {
        this.statusTransaction = statusTransaction;
    }

    public Float getMontant() {
        return montant;
    }

    public void setMontant(Float montant) {
        this.montant = montant;
    }

    public Set<ModaliteTransaction> getModaliteTransactionSet() {
        return modaliteTransactionSet;
    }

    public void setModaliteTransactionSet(Set<ModaliteTransaction> modaliteTransactionSet) {
        this.modaliteTransactionSet = modaliteTransactionSet;
    }
}
