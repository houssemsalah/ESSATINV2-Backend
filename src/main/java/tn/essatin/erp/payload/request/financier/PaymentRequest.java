package tn.essatin.erp.payload.request.financier;

import tn.essatin.erp.model.financier.ModaliteTransaction;

import javax.validation.constraints.NotNull;
import java.util.Set;

public class PaymentRequest {
    @NotNull
    private int idEnregistrement;
    @NotNull
    private Set<ModaliteTransaction> modaliteTransactionSet;
    @NotNull
    private int idCompteFinancier;

    public PaymentRequest(int idEnregistrement, Set<ModaliteTransaction> modaliteTransactionSet, int idCompteFinancier) {
        this.idEnregistrement = idEnregistrement;
        this.modaliteTransactionSet = modaliteTransactionSet;
        this.idCompteFinancier = idCompteFinancier;
    }

    public int getIdEnregistrement() {
        return idEnregistrement;
    }

    public Set<ModaliteTransaction> getModaliteTransactionSet() {
        return modaliteTransactionSet;
    }

    public int getIdCompteFinancier() {
        return idCompteFinancier;
    }


}
